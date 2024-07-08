package com.tournament.probability.regression.logistic;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.Instances;

import java.util.ArrayList;

public record DataReturnedFromLogisticDTO(Evaluation eval,
                                          Evaluation evalTest,
                                          Logistic model,
                                          ArrayList<Attribute> attributes,
                                          Instances testData) {
}
