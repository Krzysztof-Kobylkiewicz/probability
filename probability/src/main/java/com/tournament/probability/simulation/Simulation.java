package com.tournament.probability.simulation;

import java.util.Random;

public class Simulation {
    private static final Random random = new Random();

    public static Character simulateGroupStageMatch(Probability prob){

        double rand = random.nextDouble();

        if (prob.getWin() == 0){
            return '2';

        } else if (prob.getLose() == 0){
            return '1';

        } else if (rand < prob.getWin()){
            return '1';

        } else if (rand < prob.getWin() + prob.getDraw()){
            return 'X';

        } else {
            return '2';

        }
    }

    public static Character simulateKnockoutStageMatch(Probability prob){

        Double rand = null;
        do {
            rand = random.nextDouble();

        } while (!(rand <= prob.getWin() || rand >= 1 - prob.getLose()));

        if (prob.getWin() == 0){
            return '2';

        } else if (prob.getLose() == 0){
            return '1';

        } else if (rand < prob.getWin()){
            return '1';

        } else {
            return '2';

        }
    }
}
