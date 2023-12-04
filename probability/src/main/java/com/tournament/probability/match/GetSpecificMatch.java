package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GetSpecificMatch {
    private final MatchRepository matchRepository;
    @Autowired
    GetSpecificMatch(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    Match getMatchByIdMethod(int id){
        return matchRepository.getReferenceById(id);
    }
}
