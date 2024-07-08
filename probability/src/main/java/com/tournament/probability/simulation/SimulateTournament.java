package com.tournament.probability.simulation;

import com.tournament.probability.regression.logistic.LogisticRegressionModel;
import com.tournament.probability.regression.MatchRankingDTO;
import com.tournament.probability.regression.RegressionRepository;
import com.tournament.probability.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.Instances;
import java.util.Comparator;


import java.util.*;

@Service
public class SimulateTournament {
    @Autowired
    private final RegressionRepository regressionRepository;

    @Autowired
    private final LogisticRegressionModel logisticRegressionModel;

    @Autowired
    private final FindDataToSimulation findDataToSimulation;

    public SimulateTournament(RegressionRepository regressionRepository,
                              LogisticRegressionModel logisticRegressionModel,
                              FindDataToSimulation findDataToSimulation) {
        this.regressionRepository = regressionRepository;
        this.logisticRegressionModel = logisticRegressionModel;
        this.findDataToSimulation = findDataToSimulation;
    }

    private final static double rand = 0.005;

    String[][] createGroupsWithRandomTeams(int id) {
        Character type = regressionRepository.findTypeById(id).getTournamentType();

        List<Team> teams = regressionRepository.findAllTeams();
        Random random = new Random();
        String[][] groups = null;
        MatchRankingDTO[][] matches = null;

        if (type.equals('W')) {
            groups = new String[8][4];
            matches = new MatchRankingDTO[8][6];

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    int randomIndex = random.nextInt(teams.size());
                    String team = teams.get(randomIndex).getName();
                    groups[i][j] = team;
                    teams.remove(randomIndex);
                }
            }
        }

        return groups;
    }

    public Map<String, Double> createEuro2024Groups(Evaluation eval,
                                           Evaluation evalTest,
                                           Logistic model,
                                           ArrayList<Attribute> attributes,
                                           Instances testData,
                                           int numSimulations) {

        attributes.remove("class");

        List<Double> probabilitiesInGroupStage = new ArrayList<>();
        List<Double> probabilitiesInKnockoutStage = new ArrayList<>();

        String[][] groups = null;

        groups = new String[][]{{"Niemcy", "Szwajcaria", "Szkocja", "Węgry"},
                {"Hiszpania", "Włochy", "Albania", "Chorwacja"},
                {"Anglia", "Dania", "Słowenia", "Serbia"},
                {"Holandia", "Francja", "Polska", "Austria"},
                {"Rumunia", "Słowacja", "Belgia", "Ukraina"},
                {"Turcja", "Portugalia", "Czechy", "Gruzja"}};

        String[][] roundOfSixteen = new String[2][8];
        String[][] quarterFinals = new String[2][4];
        String[][] semiFinals = new String[2][2];
        String[] final_ = new String[2];

        Map<String, Integer> groupResults = new HashMap<>();
        List<TeamAndPoints> thirdPlacesTable = new ArrayList<>();
        String winner = null;
        List<String> winners = new ArrayList<>();

        for (int n = 0; n < numSimulations; n++) {

            System.out.println("\nSIMULATION NUMBER: " + n + "\n");

            for (int i = 0; i < 6; i++) {

                probabilitiesInGroupStage.clear();
                List<Character> results = new ArrayList<>();
                List<Probability> matches = new ArrayList<>();

                for (int j = 0; j < 4; j++) {

                    String currentTeam = groups[i][j];

                    List<String> opponents = new ArrayList<>();
                    for (String team : groups[i]) {
                        if (!team.equals(currentTeam)) {
                            opponents.add(team);
                        }
                    }

                    for (int b = 0; b < 4; b++) {
                        if (b == 0) {
                            groupResults.put(currentTeam, 0);
                        } else {
                            groupResults.put(opponents.get(b - 1), 0);
                        }
                    }

                    if (j == 0) {
                        matches.add(new Probability(currentTeam, opponents.get(0)));
                        matches.add(new Probability(currentTeam, opponents.get(1)));
                        matches.add(new Probability(currentTeam, opponents.get(2)));
                        matches.add(new Probability(opponents.get(0), opponents.get(1)));
                        matches.add(new Probability(opponents.get(0), opponents.get(2)));
                        matches.add(new Probability(opponents.get(1), opponents.get(2)));
                    }

                    probabilitiesInGroupStage = findDataToSimulation.findDataToSimulation(currentTeam, attributes,
                                                                                            testData, rand,
                                                                                            opponents, model,
                                                                                            probabilitiesInGroupStage);
                }

                /*  Team 1 vs Team 2
                 *   Team 1 vs Team 3
                 *   Team 1 vs Team 4
                 *   Team 2 vs Team 3
                 *   Team 2 vs Team 4
                 *   Team 3 vs Team 4    */

                matches.get(0).setWin(probabilitiesInGroupStage.get(0));
                matches.get(0).setDraw(1 - (probabilitiesInGroupStage.get(0) + probabilitiesInGroupStage.get(3)));
                matches.get(0).setLose(probabilitiesInGroupStage.get(3));

                matches.get(1).setWin(probabilitiesInGroupStage.get(1));
                matches.get(1).setDraw(1 - (probabilitiesInGroupStage.get(1) + probabilitiesInGroupStage.get(6)));
                matches.get(1).setLose(probabilitiesInGroupStage.get(6));

                matches.get(2).setWin(probabilitiesInGroupStage.get(2));
                matches.get(2).setDraw(1 - (probabilitiesInGroupStage.get(2) + probabilitiesInGroupStage.get(9)));
                matches.get(2).setLose(probabilitiesInGroupStage.get(9));

                matches.get(3).setWin(probabilitiesInGroupStage.get(4));
                matches.get(3).setDraw(1 - (probabilitiesInGroupStage.get(4) + probabilitiesInGroupStage.get(7)));
                matches.get(3).setLose(probabilitiesInGroupStage.get(7));

                matches.get(4).setWin(probabilitiesInGroupStage.get(5));
                matches.get(4).setDraw(1 - (probabilitiesInGroupStage.get(5) + probabilitiesInGroupStage.get(10)));
                matches.get(4).setLose(probabilitiesInGroupStage.get(10));

                matches.get(5).setWin(probabilitiesInGroupStage.get(8));
                matches.get(5).setDraw(1 - (probabilitiesInGroupStage.get(8) + probabilitiesInGroupStage.get(11)));
                matches.get(5).setLose(probabilitiesInGroupStage.get(11));

                for (int t = 0; t < matches.size(); t++) {

                    results.add(Simulation.simulateGroupStageMatch(matches.get(t)));

                    switch (results.get(t)) {
                        case '1':
                            groupResults.put(matches.get(t).getHost(), groupResults.get(matches.get(t).getHost()) + 3);
                            break;
                        case 'X':
                            groupResults.put(matches.get(t).getHost(), groupResults.get(matches.get(t).getHost()) + 1);
                            groupResults.put(matches.get(t).getVisitor(), groupResults.get(matches.get(t).getVisitor()) + 1);
                            break;
                        case '2':
                            groupResults.put(matches.get(t).getVisitor(), groupResults.get(matches.get(t).getVisitor()) + 3);
                            break;
                    }
                }

                List<TeamAndPoints> group = new ArrayList<>();

                for (int j = 0; j < 4; j++) {
                    if (j == 0) {
                        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=");
                    }
                    System.out.println(groups[i][j] + " - " + groupResults.get(groups[i][j]) + "pts");

                    group.add(new TeamAndPoints(groups[i][j], groupResults.get(groups[i][j])));

                    if (j == 3) {
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=");
                    }
                }

                Collections.sort(group, new Comparator<TeamAndPoints>() {
                    @Override
                    public int compare(TeamAndPoints t1, TeamAndPoints t2) {
                        return Integer.compare(t2.getPoints(), t1.getPoints());
                    }
                });

                //System.out.println("SORTED GROUP: \n " + group.toString() + "\n\n");

                thirdPlacesTable.add(group.get(2));

            /* 1/8
             1st A vs 2nd C
             2nd A vs 2nd B
             1st C vs 3rd best from 3rd places
             1st B vs 2nd best from 3rd places
             2nd D vs 2nd E
             1st F vs 4th best from 3rd places
             1st E vs 1st best from 3rd places
             1st D vs 2nd F */

                switch (i) {
                    case 0:
                        roundOfSixteen[0][0] = group.get(0).getName();
                        roundOfSixteen[0][1] = group.get(1).getName();
                        break;
                    case 1:
                        roundOfSixteen[1][1] = group.get(1).getName();
                        roundOfSixteen[0][3] = group.get(0).getName();
                        break;
                    case 2:
                        roundOfSixteen[0][2] = group.get(0).getName();
                        roundOfSixteen[1][0] = group.get(1).getName();
                        break;
                    case 3:
                        roundOfSixteen[0][7] = group.get(0).getName();
                        roundOfSixteen[0][4] = group.get(1).getName();
                        break;
                    case 4:
                        roundOfSixteen[1][4] = group.get(1).getName();
                        roundOfSixteen[0][6] = group.get(0).getName();
                        break;
                    case 5:
                        roundOfSixteen[0][5] = group.get(0).getName();
                        roundOfSixteen[1][7] = group.get(1).getName();
                        break;
                }

            }

            Collections.sort(thirdPlacesTable, new Comparator<TeamAndPoints>() {
                @Override
                public int compare(TeamAndPoints t1, TeamAndPoints t2) {
                    return Integer.compare(t2.getPoints(), t1.getPoints());
                }
            });

            roundOfSixteen[1][2] = thirdPlacesTable.get(2).getName();
            roundOfSixteen[1][3] = thirdPlacesTable.get(1).getName();
            roundOfSixteen[1][5] = thirdPlacesTable.get(3).getName();
            roundOfSixteen[1][6] = thirdPlacesTable.get(0).getName();

            System.out.println("\nROUND OF SIXTEEN: \n");
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 2; x++) {
                    System.out.print(roundOfSixteen[x][y] + " ");
                }
                System.out.println(" ");
            }

            List<Probability> matches = new ArrayList<>();

            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 2; x++) {

                    String currentTeam = roundOfSixteen[x][y];

                    String opponent = null;
                    if (roundOfSixteen[0][y].equals(currentTeam)) {
                        opponent = roundOfSixteen[1][y];
                    } else {
                        opponent = roundOfSixteen[0][y];
                    }

                    MatchesAndProbabilitiesDTO matchesAndProbabilitiesDTO = findDataToSimulation.findDataToSimulation(currentTeam, attributes,
                                                                                                                        testData, rand,
                                                                                                                        opponent, model,
                                                                                                                        probabilitiesInKnockoutStage, x,
                                                                                                                        matches);

                    probabilitiesInKnockoutStage = matchesAndProbabilitiesDTO.probabilitiesInKnockoutStage();
                    matches = matchesAndProbabilitiesDTO.matches();
                }
            }

            int k = 0;
            for (Probability match : matches) {
                match.setWin(probabilitiesInKnockoutStage.get(k));
                match.setDraw(1 - (probabilitiesInKnockoutStage.get(k) + probabilitiesInKnockoutStage.get(k + 1)));
                match.setLose(probabilitiesInKnockoutStage.get(k + 1));
                k += 2;
            }

            List<Character> results = new ArrayList<>();

            for (int t = 0; t < matches.size(); t++) {

                results.add(Simulation.simulateKnockoutStageMatch(matches.get(t)));
            }

            /*  1/4
             * 1 vs 4
             * 2 vs 3
             * 7 vs 8
             * 5 vs 6
             * */

            switch (results.get(0)) {
                case '1':
                    quarterFinals[0][0] = matches.get(0).getHost();
                    break;
                case '2':
                    quarterFinals[0][0] = matches.get(0).getVisitor();
                    break;
            }
            switch (results.get(1)) {
                case '1':
                    quarterFinals[0][1] = matches.get(1).getHost();
                    break;
                case '2':
                    quarterFinals[0][1] = matches.get(1).getVisitor();
            }
            switch (results.get(2)) {
                case '1':
                    quarterFinals[1][1] = matches.get(2).getHost();
                    break;
                case '2':
                    quarterFinals[1][1] = matches.get(2).getVisitor();
            }
            switch (results.get(3)) {
                case '1':
                    quarterFinals[1][0] = matches.get(3).getHost();
                    break;
                case '2':
                    quarterFinals[1][0] = matches.get(3).getVisitor();
                    break;
            }
            switch (results.get(4)) {
                case '1':
                    quarterFinals[0][3] = matches.get(4).getHost();
                    break;
                case '2':
                    quarterFinals[0][3] = matches.get(4).getVisitor();
            }
            switch (results.get(5)) {
                case '1':
                    quarterFinals[1][3] = matches.get(5).getHost();
                    break;
                case '2':
                    quarterFinals[1][3] = matches.get(5).getVisitor();
            }
            switch (results.get(6)) {
                case '1':
                    quarterFinals[0][2] = matches.get(6).getHost();
                    break;
                case '2':
                    quarterFinals[0][2] = matches.get(6).getVisitor();
            }
            switch (results.get(7)) {
                case '1':
                    quarterFinals[1][2] = matches.get(7).getHost();
                    break;
                case '2':
                    quarterFinals[1][2] = matches.get(7).getVisitor();
                    break;
            }
            System.out.println("\nQUARTER FINALS:\n");
            for (int t = 0; t < 4; t++) {
                for (int p = 0; p < 2; p++) {
                    System.out.print(quarterFinals[p][t] + " ");
                }
                System.out.println(" ");
            }

            matches.clear();
            probabilitiesInKnockoutStage.clear();
            results.clear();
            thirdPlacesTable.clear();

            for (int t = 0; t < 4; t++) {
                for (int p = 0; p < 2; p++) {

                    String currentTeam = quarterFinals[p][t];

                    String opponent = null;
                    if (quarterFinals[0][t].equals(currentTeam)) {
                        opponent = quarterFinals[1][t];
                    } else {
                        opponent = quarterFinals[0][t];
                    }

                    MatchesAndProbabilitiesDTO matchesAndProbabilitiesDTO = findDataToSimulation.findDataToSimulation(currentTeam, attributes,
                                                                                                                        testData, rand,
                                                                                                                        opponent, model,
                                                                                                                        probabilitiesInKnockoutStage, p,
                                                                                                                        matches);

                    probabilitiesInKnockoutStage = matchesAndProbabilitiesDTO.probabilitiesInKnockoutStage();
                    matches = matchesAndProbabilitiesDTO.matches();
                }
            }

            k = 0;
            for (Probability match : matches) {
                match.setWin(probabilitiesInKnockoutStage.get(k));
                match.setDraw(1 - (probabilitiesInKnockoutStage.get(k) + probabilitiesInKnockoutStage.get(k + 1)));
                match.setLose(probabilitiesInKnockoutStage.get(k + 1));
                k += 2;
            }

            for (Probability match : matches) {
                results.add(Simulation.simulateKnockoutStageMatch(match));
            }

            switch (results.get(0)) {
                case '1':
                    semiFinals[0][0] = matches.get(0).getHost();
                    break;
                case '2':
                    semiFinals[0][0] = matches.get(0).getVisitor();
                    break;
            }
            switch (results.get(1)) {
                case '1':
                    semiFinals[1][0] = matches.get(1).getHost();
                    break;
                case '2':
                    semiFinals[1][0] = matches.get(1).getVisitor();
                    break;
            }
            switch (results.get(2)) {
                case '1':
                    semiFinals[0][1] = matches.get(2).getHost();
                    break;
                case '2':
                    semiFinals[0][1] = matches.get(2).getVisitor();
                    break;
            }
            switch (results.get(3)) {
                case '1':
                    semiFinals[1][1] = matches.get(3).getHost();
                    break;
                case '2':
                    semiFinals[1][1] = matches.get(3).getVisitor();
                    break;
            }

            System.out.println("\nSEMI FINALS:\n");
            for (int c = 0; c < 2; c++) {
                for (int q = 0; q < 2; q++) {
                    System.out.print(semiFinals[q][c] + " ");
                }
                System.out.println(" ");
            }

            matches.clear();
            probabilitiesInKnockoutStage.clear();
            results.clear();

            for (int t = 0; t < 2; t++) {
                for (int p = 0; p < 2; p++) {
                    String currentTeam = semiFinals[p][t];

                    String opponent = null;
                    if (semiFinals[0][t].equals(currentTeam)) {
                        opponent = semiFinals[1][t];
                    } else {
                        opponent = semiFinals[0][t];
                    }

                    MatchesAndProbabilitiesDTO matchesAndProbabilitiesDTO = findDataToSimulation.findDataToSimulation(currentTeam, attributes,
                                                                                                                        testData, rand,
                                                                                                                        opponent, model,
                                                                                                                        probabilitiesInKnockoutStage, p,
                                                                                                                        matches);

                    probabilitiesInKnockoutStage = matchesAndProbabilitiesDTO.probabilitiesInKnockoutStage();
                    matches = matchesAndProbabilitiesDTO.matches();
                }
            }

            k = 0;
            for (Probability match : matches) {
                match.setWin(probabilitiesInKnockoutStage.get(k));
                match.setDraw(1 - (probabilitiesInKnockoutStage.get(k) + probabilitiesInKnockoutStage.get(k + 1)));
                match.setLose(probabilitiesInKnockoutStage.get(k + 1));
                k += 2;
            }

            for (Probability match : matches) {
                results.add(Simulation.simulateKnockoutStageMatch(match));
            }

            switch (results.get(0)) {
                case '1':
                    final_[0] = matches.get(0).getHost();
                    break;
                case '2':
                    final_[0] = matches.get(0).getVisitor();
                    break;
            }
            switch (results.get(1)) {
                case '1':
                    final_[1] = matches.get(1).getHost();
                    break;
                case '2':
                    final_[1] = matches.get(1).getVisitor();
                    break;
            }

            System.out.println("\nFINAL:\n");
            System.out.print(final_[0] + " - " + final_[1]);

            matches.clear();
            probabilitiesInKnockoutStage.clear();
            results.clear();

            for (int p = 0; p < 2; p++) {

                String currentTeam = final_[p];

                String opponent = null;

                switch (p) {
                    case 0:
                        opponent = final_[1];
                        break;
                    case 1:
                        opponent = final_[0];
                        break;
                }

                MatchesAndProbabilitiesDTO matchesAndProbabilitiesDTO = findDataToSimulation.findDataToSimulation(currentTeam, attributes,
                                                                                                                    testData, rand,
                                                                                                                    opponent, model,
                                                                                                                    probabilitiesInKnockoutStage, p,
                                                                                                                    matches);

                probabilitiesInKnockoutStage = matchesAndProbabilitiesDTO.probabilitiesInKnockoutStage();
                matches = matchesAndProbabilitiesDTO.matches();
            }

            k = 0;
            for (Probability match : matches) {
                match.setWin(probabilitiesInKnockoutStage.get(k));
                match.setDraw(1 - (probabilitiesInKnockoutStage.get(k) + probabilitiesInKnockoutStage.get(k + 1)));
                match.setLose(probabilitiesInKnockoutStage.get(k + 1));
                k += 2;
            }

            for (Probability match : matches) {
                results.add(Simulation.simulateKnockoutStageMatch(match));
            }

            switch (results.get(0)) {
                case '1':
                    winner = matches.get(0).getHost();
                    break;
                case '2':
                    winner = matches.get(0).getVisitor();
                    break;
            }
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("\nWINNER: " + winner + "\n");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

            winners.add(winner);
        }

        Map<String, Integer> occurrences = new HashMap<>();

        for (String w : winners){
            occurrences.put(w, occurrences.getOrDefault(w, 0) + 1);
        }

        Map<String, Double> probabilities = new HashMap<>();

        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            String w = entry.getKey();
            int count = entry.getValue();
            double probability = (double) count / numSimulations;
            probabilities.put(w, probability);
        }
        System.out.println("\n");
        for (Map.Entry<String, Double> entry : probabilities.entrySet()) {
            System.out.println("Winner: " + entry.getKey() + ", probability: " + entry.getValue() + ".\n");
        }

        return probabilities;
    }
}
