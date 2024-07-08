package com.tournament.probability.simulation;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateProbabilityOfWin {

    Map<String, Double> calculateProbabilityOfWin(List<String> winners,
                                                  int numSimulations){

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
