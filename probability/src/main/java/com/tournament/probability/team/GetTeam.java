package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GetTeam {
    private final TeamRepository teamRepository;
    @Autowired
    public GetTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    Team getTeamByName(String name){
        return teamRepository.getTeamByName(name);
    }
}
