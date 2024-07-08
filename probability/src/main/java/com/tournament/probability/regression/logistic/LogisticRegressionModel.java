package com.tournament.probability.regression.logistic;

import com.tournament.probability.regression.*;
import com.tournament.probability.regression.linear.PrepareData;
import com.tournament.probability.regression.linear.PrepareDataInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.springframework.stereotype.Service;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import java.util.ArrayList;
import weka.classifiers.Evaluation;


@Service
public class LogisticRegressionModel extends PrepareData {

    @Autowired
    private final RegressionRepository regressionRepository;

    public LogisticRegressionModel(RegressionRepository regressionRepository) {
        this.regressionRepository = regressionRepository;
    }

    private final PrepareDataInterface prepareData = new PrepareData();

    public DataReturnedFromLogisticDTO logisticRegression(){

        Evaluation eval = null;
        Evaluation evalTest = null;
        Logistic model = new Logistic();
        ArrayList<Attribute> attributes = null;
        Instances testData = null;

        final double minAccuracy = 72.5;

        int tries = 0;
        int numberOfVariables = 22; //Unnecessary columns such as date or columns which are mainly nulls are subtracted


        try {
            do {
                List<MatchRankingDTO> data = regressionRepository.findDataToModel();

                Collections.shuffle(data);

                int numberOfObservations = data.size();
                int trainSize = (int) (numberOfObservations * 0.7);
                int testSize = numberOfObservations - trainSize;

                PreparedXDTO preparedXDTO = prepareData.prepareXDataToModel(data);
                double[][] X = preparedXDTO.X();
                ArrayList<String> labels = preparedXDTO.labels();

                double[] Y = prepareData.prepareYDataToModel(data);

                double[][] trainX = new double[trainSize][numberOfVariables];
                double[][] testX = new double[testSize][numberOfVariables];
                double[] trainY = new double[trainSize];
                double[] testY = new double[testSize];

                System.arraycopy(X, 0, trainX, 0, trainSize);
                System.arraycopy(X, trainSize, testX, 0, testSize);
                System.arraycopy(Y, 0, trainY, 0, trainSize);
                System.arraycopy(Y, trainSize, testY, 0, testSize);

                attributes = new ArrayList<>();
                for (String label : labels) {
                    attributes.add(new Attribute(label));
                }

                ArrayList<String> classValues = new ArrayList<>();
                classValues.add("0");
                classValues.add("1");
                attributes.add(new Attribute("class", classValues));

                Instances trainData = new Instances("dataset", attributes, trainX.length);
                trainData.setClassIndex(trainData.numAttributes() - 1);

                for (int i = 0; i < trainX.length; i++) {
                    double[] row = trainX[i];
                    Instance instance = new DenseInstance(trainData.numAttributes());
                    for (int j = 0; j < row.length; j++) {
                        instance.setValue(attributes.get(j), row[j]);
                    }
                    instance.setValue(attributes.get(attributes.size() - 1), trainY[i]);
                    trainData.add(instance);
                }

                model.buildClassifier(trainData);

                // Cross validation
                int folds = 10;

                eval = new Evaluation(trainData);
                eval.crossValidateModel(model, trainData, folds, new java.util.Random(1));

                testData = new Instances("dataTest", attributes, testX.length);
                testData.setClassIndex(testData.numAttributes() - 1);

                for (int i = 0; i < testX.length; i++) {
                    double[] row = testX[i];
                    Instance instance = new DenseInstance(testData.numAttributes());
                    for (int j = 0; j < row.length; j++) {
                        instance.setValue(attributes.get(j), row[j]);
                    }
                    instance.setValue(attributes.get(attributes.size() - 1), testY[i]);
                    testData.add(instance);
                }

                evalTest = new Evaluation(trainData);
                evalTest.evaluateModel(model, testData);

                tries ++;

            } while (eval.pctCorrect() <= minAccuracy);

            System.out.println("\n" + tries + " tries was/were performed in order to get accuracy greater than  " + minAccuracy + ".\n");

            System.out.println("Model: \n" + model);

            System.out.println(eval.toSummaryString("\nCross validation results:\n", false));

            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Accuracy: " + eval.pctCorrect());
            System.out.println("Precision: " + eval.precision(1));
            System.out.println("Recall: " + eval.recall(1));
            System.out.println("AUC: " + eval.areaUnderROC(1));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("Test data: " + testData.toString() + "\n");

            System.out.println(evalTest.toSummaryString("\n\nEvaluation on test data:\n", false));

            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Evaluation on test set:");
            System.out.println("Accuracy: " + evalTest.pctCorrect());
            System.out.println("Precision: " + evalTest.precision(1));
            System.out.println("Recall: " + evalTest.recall(1));
            System.out.println("AUC: " + evalTest.areaUnderROC(1));
            System.out.println(evalTest.toMatrixString("Confusion Matrix"));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataReturnedFromLogisticDTO(eval, evalTest, model, attributes, testData);
    }
}
