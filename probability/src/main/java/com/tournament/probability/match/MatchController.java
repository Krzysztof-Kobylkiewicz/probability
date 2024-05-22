package com.tournament.probability.match;

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
    public ResponseEntity<Match> addMatchMethod(@RequestBody Match match){
        Match createdMatch = matchService.addMatchMethod(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Optional<Match>> getMatchByIdMethod(@RequestParam int id){
        Optional<Match> optionalMatch = matchService.getMatchById(id);
        return new ResponseEntity<>(optionalMatch, HttpStatus.OK);
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Match> updateMatchMethod (@PathVariable int id, @RequestBody Match match){
        Match responseMatch = matchService.updateMatch(id, match);
        return new ResponseEntity<>(responseMatch, HttpStatus.CREATED);
    }
}