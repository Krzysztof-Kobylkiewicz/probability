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

    Tournament updateTournamentById(int id, Tournament tournament){

        Tournament tournamentToUpdate = tournamentRepository.findTournamentById(id)
                .orElseThrow(() -> new IllegalStateException("Tournament with id '" + id + "' does not exist."));

        tournamentToUpdate.setId(tournament.getId());
        tournamentToUpdate.setName(tournament.getName());
        tournamentToUpdate.setStartDate(tournament.getStartDate());
        tournamentToUpdate.setEndDate(tournament.getEndDate());
        tournamentToUpdate.setTeamsParticipating(tournament.getTeamsParticipating());
        tournamentToUpdate.setWinner(tournament.getWinner());

        tournamentRepository.save(tournamentToUpdate);
        return tournamentToUpdate;
    }

    Tournament getTournamentById(int id){

        return tournamentRepository.findTournamentById(id)
                .orElseThrow(() -> new IllegalStateException("Tournament with id '" + id + "' does not exist."));
    }

    void deleteTournamentById(int id){
        tournamentRepository.deleteTournamentById(id);
    }
}