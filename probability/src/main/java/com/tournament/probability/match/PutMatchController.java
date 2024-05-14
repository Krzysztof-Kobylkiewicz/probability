package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/match")
public class PutMatchController {
    private final UpdateMatch updateMatch;
    @Autowired
    PutMatchController(UpdateMatch updateMatch) {
        this.updateMatch = updateMatch;
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Match> updateMatchMethod (@PathVariable int id, @RequestBody Match match){
        Match responseMatch = updateMatch.updateMatchMethod(id, match);
        return new ResponseEntity<>(responseMatch, HttpStatus.CREATED);
    }
}