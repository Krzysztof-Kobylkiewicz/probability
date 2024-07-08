package com.tournament.probability.regression.linear;

import com.tournament.probability.regression.MatchRankingDTO;
import com.tournament.probability.regression.PreparedXDTO;

import java.util.List;

public interface PrepareDataInterface {
    PreparedXDTO prepareXDataToModel(List<MatchRankingDTO> data);
    double[] prepareYDataToModel(List<MatchRankingDTO> data);
}
