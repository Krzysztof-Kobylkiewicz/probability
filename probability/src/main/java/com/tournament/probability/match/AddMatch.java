package com.tournament.probability.match;

import com.tournament.probability.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AddMatch {
    private final MatchRepository matchRepository;
    @Autowired
    AddMatch(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    Match addMatchMethod(Match match){
        matchRepository.save(match);
        return match;
    }
}