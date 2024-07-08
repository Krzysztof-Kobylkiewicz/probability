package com.tournament.probability.simulation;

import java.util.List;

public record MatchesAndProbabilitiesDTO(List<Probability> matches,
                                         List<Double> probabilitiesInKnockoutStage) {
}
