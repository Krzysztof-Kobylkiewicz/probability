package com.tournament.probability.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RankingService {

    @Autowired
    private final RankingRepository rankingRepository;

    RankingService(RankingRepository rankingRepository){this.rankingRepository = rankingRepository;
    }

    Ranking addRanking(Ranking ranking){
        return rankingRepository.save(ranking);
    }

    Ranking getRankingById(int id){

        Ranking returnedRanking = rankingRepository.findRankingById(id)
                .orElseThrow(() -> new IllegalStateException("Ranking with id '" + id + "' does not exist."));
        return returnedRanking;
    }

    Ranking updateRankingById(int id, Ranking ranking){

        Ranking rankingToUpdate = rankingRepository.findRankingById(id)
                .orElseThrow(() -> new IllegalStateException("Ranking with id '" + id + "' does not exist."));

        rankingToUpdate.setId(ranking.getId());
        rankingToUpdate.setPosition(ranking.getPosition());
        rankingToUpdate.setRankingDate(ranking.getRankingDate());
        rankingToUpdate.setConfederation(ranking.getConfederation());
        rankingToUpdate.setTeamId(ranking.getTeamId());
        rankingToUpdate.setAverageAge(ranking.getAverageAge());
        rankingToUpdate.setNumberOfPlayers(ranking.getNumberOfPlayers());
        rankingToUpdate.setTeamValue(ranking.getTeamValue());

        rankingRepository.save(rankingToUpdate);

        return ranking;
    }

    void deleteRankingById(int id){

        Ranking rankingToDelete = rankingRepository.findRankingById(id)
                .orElseThrow(() -> new IllegalStateException("Ranking with id '" + id + "' does not exist."));

        rankingRepository.deleteRankingById(id);
    }
}