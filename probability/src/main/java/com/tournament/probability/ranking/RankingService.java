package com.tournament.probability.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddRanking {
    private final RankingRepository rankingRepository;
    @Autowired
    AddRanking(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }
    Ranking postRankingMethod(Ranking ranking){
        rankingRepository.save(ranking);
        return ranking;
    }
}