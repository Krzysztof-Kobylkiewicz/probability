package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTournamentType {
    private final TournamentTypeRepository tournamentTypeRepository;
    @Autowired
    public DeleteTournamentType(TournamentTypeRepository tournamentTypeRepository) {
        this.tournamentTypeRepository = tournamentTypeRepository;
    }
    void deleteTournamentTypeById(int id){
        tournamentTypeRepository.deleteTournamentTypeById(id);
    }
}
