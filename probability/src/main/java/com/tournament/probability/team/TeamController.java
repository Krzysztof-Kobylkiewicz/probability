package com.tournament.probability.team;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/team")
public class TeamController {
    @Autowired
    private final TeamService teamService;

    TeamController(TeamService teamService){
        this.teamService = teamService;
    }
    @GetMapping(path = "/get/all")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }
    @GetMapping(path = "/get/{name}")
    public ResponseEntity<Optional<Team>> getTeam(@PathVariable String name){
        Optional<Team> team = teamService.getTeamByName(name);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Team> addTeamMethod(@RequestBody Team team) {
        Team createdTeam = teamService.addTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Team> updateTeamById(@PathVariable int id, @RequestBody Team team){
        Team updatedTeam = teamService.updateTeamById(id, team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Team> deleteTeamById(@PathVariable("id") int id){
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}