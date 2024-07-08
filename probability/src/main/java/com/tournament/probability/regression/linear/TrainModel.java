package com.tournament.probability.regression.linear;

import com.tournament.probability.regression.MatchRankingDTO;
import com.tournament.probability.regression.PreparedXDTO;
import com.tournament.probability.regression.RegressionRepository;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class TrainModel extends PrepareData {

    @Autowired
    private final RegressionRepository regressionRepository;

    @Autowired
    private final CoefficientsToJson coefficientsToJson;


    TrainModel(RegressionRepository regressionRepository,
               CoefficientsToJson coefficientsToJson){
        this.coefficientsToJson = coefficientsToJson;
        this.regressionRepository = regressionRepository;
    }

    private final PrepareDataInterface prepareData = new PrepareData();

    DataReturnedFromTrainedModelDTO trainModel(){

        List<MatchRankingDTO> data = regressionRepository.findDataToModel();

        Collections.shuffle(data);

        int numberOfVariables = 22; //Unnecessary columns such as date or columns which are mainly nulls are subtracted
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

            OLSMultipleLinearRegression linearRegressionModel = new OLSMultipleLinearRegression();

            linearRegressionModel.newSampleData(trainY, trainX);

            double [] olsCoefficients = linearRegressionModel.estimateRegressionParameters();

            double[] standardErrors = linearRegressionModel.estimateRegressionParametersStandardErrors();

            double[] pValues = new double[olsCoefficients.length];
            TDistribution tDistribution = new TDistribution(numberOfObservations - numberOfVariables);

            for (int i = 0; i < olsCoefficients.length; i++) {
                double tStat = olsCoefficients[i] / standardErrors[i];
                double pValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tStat)));
                pValues[i] = pValue;
            }
            System.out.print("\np-Values:\n");
            for (int i = 0; i < labels.size(); i++) {
                System.out.println(labels.get(i) + " : " + pValues[i]);
                if (pValues[i] < 0.05) {
                    System.out.print(" SIGNIFICANT");
                }
            }

            double rSquared = linearRegressionModel.calculateRSquared();

            String jsonString = coefficientsToJson.coefficientsToJson(olsCoefficients, labels);


            return new DataReturnedFromTrainedModelDTO(olsCoefficients, linearRegressionModel, jsonString);

    }

}
