package com.tournament.probability.simulation;

import com.tournament.probability.ranking.Ranking;
import com.tournament.probability.regression.logistic.MatchAvgDataDTO;
import com.tournament.probability.regression.RegressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class FindDataToSimulation {

    @Autowired
    private final RegressionRepository regressionRepository;

    @Autowired
    private final AverageDataToPrediction averageDataToPrediction;

    public FindDataToSimulation(RegressionRepository regressionRepository,
                                AverageDataToPrediction averageDataToPrediction) {
        this.regressionRepository = regressionRepository;
        this.averageDataToPrediction = averageDataToPrediction;
    }

    List<Double> findDataToSimulation(String currentTeam,
                                      ArrayList<Attribute> attributes,
                                      Instances testData,
                                      double rand,
                                      List<String> opponents,
                                      Logistic model,
                                      List<Double> probabilitiesInGroupStage) {

        Optional<MatchAvgDataDTO> currentTeamPreviousMatchesOnAverage = regressionRepository.findDataAboutSpecificTeamByName(currentTeam);

        Ranking currentTeamRankingInfo = regressionRepository.findRankingDataByName(currentTeam);

        if (currentTeamPreviousMatchesOnAverage.isPresent() &&
                currentTeamPreviousMatchesOnAverage.get().getHostFreeKicks() != null) {

            // UEFA - 0
            currentTeamPreviousMatchesOnAverage.get().setHostConfederation(0);
            currentTeamPreviousMatchesOnAverage.get().setAwayConfederation(0);

            currentTeamPreviousMatchesOnAverage.get().setHostPoints(currentTeamRankingInfo.getPoints());
            currentTeamPreviousMatchesOnAverage.get().setHostPosition(currentTeamRankingInfo.getPosition());

            Instance avgToPredict = averageDataToPrediction.avgToPredict(attributes.size(),
                    testData,
                    currentTeamPreviousMatchesOnAverage,
                    rand);

            for (String opponent : opponents) {
                Ranking opponentTeamRankingInfo = regressionRepository.findRankingDataByName(opponent);

                currentTeamPreviousMatchesOnAverage.get().setAwayPoints(opponentTeamRankingInfo.getPoints());
                currentTeamPreviousMatchesOnAverage.get().setAwayPosition(opponentTeamRankingInfo.getPosition());

                avgToPredict.setValue(testData.attribute("AwayPosition"), currentTeamPreviousMatchesOnAverage.get().getAwayPosition());
                avgToPredict.setValue(testData.attribute("AwayPoints"), currentTeamPreviousMatchesOnAverage.get().getAwayPoints());


                try {

                    double[] probabilities = model.distributionForInstance(avgToPredict);
                    probabilitiesInGroupStage.add(probabilities[1]);
                    //System.out.println("'" + currentTeam +"' probability of wining against '" + opponent + "': " + probabilities[1] + "%");

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        } else {

            probabilitiesInGroupStage.add(0.0);
            probabilitiesInGroupStage.add(0.0);
            probabilitiesInGroupStage.add(0.0);

            System.out.println("No previous data available for '" + currentTeam + "' and therefore probability cannot be calculated.");
        }
        return probabilitiesInGroupStage;
    }

    MatchesAndProbabilitiesDTO findDataToSimulation(String currentTeam,
                                                    ArrayList<Attribute> attributes,
                                                    Instances testData,
                                                    double rand,
                                                    String opponent,
                                                    Logistic model,
                                                    List<Double> probabilitiesInKnockoutStage,
                                                    int x,
                                                    List<Probability> matches) {

        Optional<MatchAvgDataDTO> currentTeamPreviousMatchesOnAverage = regressionRepository.findDataAboutSpecificTeamByName(currentTeam);
        Ranking currentTeamRankingInfo = regressionRepository.findRankingDataByName(currentTeam);

        if (currentTeamPreviousMatchesOnAverage.isPresent() &&
                currentTeamPreviousMatchesOnAverage.get().getHostFreeKicks() != null) {

            currentTeamPreviousMatchesOnAverage.get().setHostConfederation(0);
            currentTeamPreviousMatchesOnAverage.get().setAwayConfederation(0);

            currentTeamPreviousMatchesOnAverage.get().setHostPoints(currentTeamRankingInfo.getPoints());
            currentTeamPreviousMatchesOnAverage.get().setHostPosition(currentTeamRankingInfo.getPosition());

            Instance avgToPredict = averageDataToPrediction.avgToPredict(attributes.size(),
                    testData,
                    currentTeamPreviousMatchesOnAverage,
                    rand);

            Ranking opponentTeamRankingInfo = regressionRepository.findRankingDataByName(opponent);

            currentTeamPreviousMatchesOnAverage.get().setAwayPoints(opponentTeamRankingInfo.getPoints());
            currentTeamPreviousMatchesOnAverage.get().setAwayPosition(opponentTeamRankingInfo.getPosition());

            avgToPredict.setValue(testData.attribute("AwayPosition"), currentTeamPreviousMatchesOnAverage.get().getAwayPosition());
            avgToPredict.setValue(testData.attribute("AwayPoints"), currentTeamPreviousMatchesOnAverage.get().getAwayPoints());

            try {

                double[] probabilities = model.distributionForInstance(avgToPredict);
                probabilitiesInKnockoutStage.add(probabilities[1]);
                //System.out.println("'" + currentTeam + "' probability of wining against '" + opponent + "': " + probabilities[1] + "%");

            } catch (Exception e) {

                e.printStackTrace();
            }
            if (x == 0) {
                matches.add(new Probability(currentTeam, opponent));
            }
        } else {
            probabilitiesInKnockoutStage.add(0.0);
            //System.out.println("'" + currentTeam + "' probability of wining against '" + opponent + "': " + 0.0 + "% (no previous data available).\n");
        }
        return new MatchesAndProbabilitiesDTO(matches, probabilitiesInKnockoutStage);
    }
}