package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private final MatchRepository matchRepository;

    MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    Match createMatch(Match match){
        matchRepository.save(match);
        return match;
    }

    Match getMatchById(int id){

        return matchRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Match with id '" + id + "' does not exist."));
    }

    Match updateMatch(int id, Match match){
        Match updatedMatch = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Match with id '" + id + "' does not exist."));

        updatedMatch.setHostId(match.getHostId());
        updatedMatch.setHostTeamGoals(match.getHostTeamGoals());
        updatedMatch.setAwayTeamGoals(match.getAwayTeamGoals());
        updatedMatch.setAwayId(match.getAwayId());

        matchRepository.save(updatedMatch);

        return match;
    };

    void deleteMatch(int id){

        Match matchToDelete = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Match with id '" + id + "' does not exist."));

        matchRepository.deleteMatchById(id);
    }
}
