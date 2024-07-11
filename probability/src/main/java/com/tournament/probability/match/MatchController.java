package com.tournament.probability.match;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/match")
public class MatchController {

    @Autowired
    private final MatchService matchService;

    MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Match> createMatch (@RequestBody Match match){
        Match createdMatch = matchService.createMatch(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable int id){
        Match returnedMatch = matchService.getMatchById(id);
        return new ResponseEntity<>(returnedMatch, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Match> updateMatch (@PathVariable int id, @RequestBody Match match){
        Match responseMatch = matchService.updateMatch(id, match);
        return new ResponseEntity<>(responseMatch, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Match> deleteMatch (@PathVariable int id){
        Match deletedMatch = matchService.deleteMatch(id);
        return new ResponseEntity<>(deletedMatch, HttpStatus.OK);
    }
}