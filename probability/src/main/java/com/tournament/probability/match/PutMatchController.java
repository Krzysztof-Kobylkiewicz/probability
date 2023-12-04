package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/match")
public class PutMatchController {
    private final AddMatch addMatch;
    @Autowired
    PutMatchController(AddMatch addMatch) {
        this.addMatch = addMatch;
    }
    @RequestMapping(path = "/add")
    public ResponseEntity<Match> addMatchMethod (@RequestBody Match match){
        Match responseMatch = addMatch.addMatchMethod(match);
        return new ResponseEntity<>(responseMatch, HttpStatus.OK);
    }
}
