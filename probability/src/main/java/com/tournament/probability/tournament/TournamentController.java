package com.tournament.probability.tournament;

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
    public ResponseEntity<Tournament> addMatch(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.addTournament(tournament);
        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }
}