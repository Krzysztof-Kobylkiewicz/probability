package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddTeam {
    private final TeamRepository teamRepository;
    @Autowired
    AddTeam(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
    Team addTeamMethod(Team team){
        Optional<Team> teamOptional = teamRepository.findTeamByName(team.getName());
        if(teamOptional.isPresent()){
            throw new IllegalStateException("Team with a given name already exist. Please provide diffrent Team name.");
        }
        return teamRepository.save(team);
    }
}