package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/match")
class PostMatchController {
    private final AddMatch addMatch;

    @Autowired
    PostMatchController(AddMatch addMatch){
        this.addMatch = addMatch;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Match> addMatchMethod(@RequestBody Match match){
        Match createdMatch = addMatch.addMatchMethod(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }
}
