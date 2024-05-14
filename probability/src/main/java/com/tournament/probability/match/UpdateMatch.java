package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class UpdateMatch {
    private final MatchRepository matchRepository;
    @Autowired
    UpdateMatch (MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    Match updateMatchMethod(int id, Match match){
        Match updatedMatch = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " does not exist."));

        updatedMatch.setHostId(match.getHostId());
        updatedMatch.setHostTeamGoals(match.getHostTeamGoals());
        updatedMatch.setAwayTeamGoals(match.getAwayTeamGoals());
        updatedMatch.setAwayId(match.getAwayId());

        matchRepository.save(updatedMatch);

        return match;
    };
}
