package com.tournament.probability.regression.linear;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

class DataReturnedFromTrainedModelDTO {
    private double[] coefficients;

    private OLSMultipleLinearRegression linearRegression;

    private String jsonString;

    public DataReturnedFromTrainedModelDTO(double[] coefficients,
                                           OLSMultipleLinearRegression linearRegression,
                                           String jsonString) {
        this.coefficients = coefficients;
        this.linearRegression = linearRegression;
        this.jsonString = jsonString;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public OLSMultipleLinearRegression getLinearRegression() {
        return linearRegression;
    }

    public void setLinearRegression(OLSMultipleLinearRegression linearRegression) {
        this.linearRegression = linearRegression;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
