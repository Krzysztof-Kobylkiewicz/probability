package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ttype")
class PostTournamentTypeController {
    private final AddTournamentType addTournamentType;
    @Autowired
    PostTournamentTypeController(AddTournamentType addTournamentType){
        this.addTournamentType = addTournamentType;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<TournamentType> AddTournamentTypeMethod(
            @RequestBody TournamentType tournamentType){
        TournamentType newTournamentType = addTournamentType.addTournamentTypeMethod(tournamentType);
        return new ResponseEntity<>(newTournamentType, HttpStatus.CREATED);
    }
}
