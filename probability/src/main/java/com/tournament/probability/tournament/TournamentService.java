package com.tournament.probability.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }
    public Tournament addTournament(Tournament tournament){
        Optional<Tournament> optionalTournament = tournamentRepository.findByName(tournament.getName());
        if (optionalTournament.isPresent()){
            throw new IllegalStateException("Tournament '" + tournament.getName() + "' already exists.");
        }
        return tournamentRepository.save(tournament);
    }
}