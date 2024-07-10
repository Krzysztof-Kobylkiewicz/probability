package com.tournament.probability.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RankingService {
    @Autowired
    private final RankingRepository rankingRepository;
    RankingService(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }
    Ranking addRanking(Ranking ranking){

        return rankingRepository.save(ranking);
    }
}