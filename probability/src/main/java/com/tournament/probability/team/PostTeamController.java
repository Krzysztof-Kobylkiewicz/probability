package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/team")
class PostTeamController {
    private final AddTeam addTeam;
    @Autowired
    PostTeamController(AddTeam addTeam){
        this.addTeam = addTeam;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Team> addTeamMethod(@RequestBody Team team) {
        Team createdTeam = addTeam.addTeamMethod(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }
}