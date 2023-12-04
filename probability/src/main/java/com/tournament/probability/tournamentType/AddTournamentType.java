package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddTournamentType {
    private final TournamentTypeRepository tournamentTypeRepository;
    @Autowired
    AddTournamentType(TournamentTypeRepository tournamentTypeRepository){
        this.tournamentTypeRepository = tournamentTypeRepository;
    }
    TournamentType addTournamentTypeMethod(TournamentType tournamentType){
        return tournamentTypeRepository.save(tournamentType);
    }
}
