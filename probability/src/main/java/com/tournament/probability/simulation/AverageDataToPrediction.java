package com.tournament.probability.simulation;

import com.tournament.probability.regression.logistic.MatchAvgDataDTO;
import org.springframework.stereotype.Service;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
class AverageDataToPrediction {

    Instance avgToPredict (int size,
                           Instances testData,
                           Optional<MatchAvgDataDTO> currentTeamPreviousMatchesOnAverage,
                           double rand){

        Instance avgToPredict = new DenseInstance(size);

        avgToPredict.setDataset(testData);

        avgToPredict.setValue(testData.attribute("HostFreeKicks"), currentTeamPreviousMatchesOnAverage.get().getHostFreeKicks() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostFreeKicks()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostFreeKicks()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayParaden"), currentTeamPreviousMatchesOnAverage.get().getAwayParaden() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayParaden()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayParaden()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostFouls"), currentTeamPreviousMatchesOnAverage.get().getHostFouls() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostFouls()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostFouls()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayFreeKicks"), currentTeamPreviousMatchesOnAverage.get().getAwayFreeKicks() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayFreeKicks()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayFreeKicks()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostCorners"), currentTeamPreviousMatchesOnAverage.get().getHostCorners() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostCorners()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostCorners()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayFouls"), currentTeamPreviousMatchesOnAverage.get().getAwayFouls() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayFouls()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayFouls()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostOffsides"), currentTeamPreviousMatchesOnAverage.get().getHostOffsides() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostOffsides()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostOffsides()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayOffsides"), currentTeamPreviousMatchesOnAverage.get().getAwayOffsides() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayOffsides()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayOffsides()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayCorners"), currentTeamPreviousMatchesOnAverage.get().getAwayCorners() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayCorners()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayCorners()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostParaden"), currentTeamPreviousMatchesOnAverage.get().getHostParaden() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostParaden()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostParaden()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostConfederation"), currentTeamPreviousMatchesOnAverage.get().getHostConfederation() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostConfederation()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostConfederation()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayConfederation"), currentTeamPreviousMatchesOnAverage.get().getAwayConfederation() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayConfederation()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayConfederation()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayAttemptsOffTarget"), currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOffTarget() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOffTarget()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOffTarget()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("AwayAttemptsOnTarget"), currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOnTarget() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOnTarget()*-rand, currentTeamPreviousMatchesOnAverage.get().getAwayAttemptsOnTarget()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostAttemptsOnTarget"), currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOnTarget() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOnTarget()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOnTarget()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostAttemptsOffTarget"), currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOffTarget() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOffTarget()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostAttemptsOffTarget()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostPoints"), currentTeamPreviousMatchesOnAverage.get().getHostPoints() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostPoints()*-rand,currentTeamPreviousMatchesOnAverage.get().getHostPoints()*rand + 0.00001));
        avgToPredict.setValue(testData.attribute("HostPosition"), currentTeamPreviousMatchesOnAverage.get().getHostPosition() + ThreadLocalRandom.current().nextDouble(currentTeamPreviousMatchesOnAverage.get().getHostPosition()*-rand, currentTeamPreviousMatchesOnAverage.get().getHostPosition()*rand + 0.00001));

        return avgToPredict;
    }
}
