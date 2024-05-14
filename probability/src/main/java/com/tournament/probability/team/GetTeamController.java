package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/team")
class GetTeamController {
    private final GetAllTeams getAllTeams;
    private final GetTeam getTeam;
    @Autowired
    GetTeamController(GetAllTeams getAllTeams, GetTeam getTeam){
        this.getAllTeams = getAllTeams;
        this.getTeam = getTeam;
    }

    @GetMapping(path = "/all")
    public List<Team> getAllTeams (){
        return getAllTeams.getAllTeams();
    }

    @GetMapping(path = "/get/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable("name") String name){
        Team gTeam = getTeam.getTeamByName(name);
        return new ResponseEntity<>(gTeam, HttpStatus.OK);
    }
}
