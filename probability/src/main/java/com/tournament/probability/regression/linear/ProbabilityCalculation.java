package com.tournament.probability.regression.linear;

class ProbabilityCalculation {
    static double calculateAccuracy(int[] actual, int[] predicted){
        if (actual.length != predicted.length) {
            throw new IllegalArgumentException("Actual and predicted arrays must have the same length");
        }
        int correct = 0;
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] == predicted[i]) {
                correct++;
            }
        }
        return (double) correct / actual.length;
    }

    public static double calculateDecisionFunction(double[] coefficients, double intercept, double[] data) {

        double z = intercept;
        for (int i = 1; i < coefficients.length; i++) {
            z += coefficients[i] * data[i-1];
        }
        return z;
    }
    public static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }
}
