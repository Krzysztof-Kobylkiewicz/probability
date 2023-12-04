package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class GetAllTeams {
    private final TeamRepository teamRepository;
    @Autowired
    GetAllTeams(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
    List<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
