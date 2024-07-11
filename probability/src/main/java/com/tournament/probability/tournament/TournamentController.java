package com.tournament.probability.tournament;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tournament")
public class TournamentController {
    @Autowired
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService){
        this.tournamentService = tournamentService;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.addTournament(tournament);
        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Tournament> updateTournamentById(@PathVariable int id, @RequestBody Tournament tournament){
        Tournament updatedTournament = tournamentService.updateTournamentById(id, tournament);
        return new ResponseEntity<>(updatedTournament, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable int id){
        Tournament tournament = tournamentService.getTournamentById(id);
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Tournament> deleteTournamentById(@PathVariable int id){
        tournamentService.deleteTournamentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}