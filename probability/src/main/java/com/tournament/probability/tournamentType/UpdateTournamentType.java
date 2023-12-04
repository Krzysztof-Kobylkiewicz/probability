package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UpdateTournamentType {
    private final TournamentTypeRepository tournamentTypeRepository;
    @Autowired
    public UpdateTournamentType(TournamentTypeRepository tournamentTypeRepository) {
        this.tournamentTypeRepository = tournamentTypeRepository;
    }
    TournamentType updateTournamentTypeMethod(TournamentType tournamentType){
           return tournamentTypeRepository.save(tournamentType);
    }
//    TournamentType updateTournamentTypeById(TournamentType tt){
//        return tournamentTypeRepository.updateTournamentTypeById(
//                tt.getTournamentType(), tt.getTournamentTypeDescription(),
//                tt.getMinTeams(), tt.getMaxTeams(), tt.getId());
//    }
}
