package com.tournament.probability.regression.linear;

import com.tournament.probability.regression.*;
import com.tournament.probability.regression.MatchRankingDTO;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class PredictUsingModel extends ProbabilityCalculation {

    @Autowired
    private final RegressionRepository regressionRepository;

    private final PrepareDataInterface prepareDataInterface = new PrepareData();

    public PredictUsingModel(RegressionRepository regressionRepository) {
        this.regressionRepository = regressionRepository;
    }

    double[] predictUsingModel(int id, OLSMultipleLinearRegression linearRegressionModel){

        List<MatchRankingDTO> sampleData = regressionRepository.findDataToModelAboutSpecificTeam(id);
        if (sampleData.isEmpty()){
            throw new NoSuchElementException("There is no available data to perform prediction for team with id = " + id);
        }

        PreparedXDTO samplePreparedXDTO = prepareDataInterface.prepareXDataToModel(sampleData);

        double[][] xSample = samplePreparedXDTO.X();

        double[] zValues = new double[xSample.length];

        double[] sampleOlsCoefficients = linearRegressionModel.estimateRegressionParameters();

        for (int i = 0; i < zValues.length; i++){

            zValues[i] = calculateDecisionFunction(sampleOlsCoefficients, sampleOlsCoefficients[0], xSample[i]);
        }

        double[] probabilities = new double[zValues.length];
        for (int i = 0; i < probabilities.length; i++){

            probabilities[i] = sigmoid(zValues[i]);
        }
        System.out.print("\nProbability: ");
        for (double probability : probabilities){
            System.out.print(probability + ", ");
        }

        return probabilities;
    }
}
