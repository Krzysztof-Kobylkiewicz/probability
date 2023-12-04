package com.tournament.probability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DeleteTeam {
    private final TeamRepository teamRepository;
    @Autowired
    public DeleteTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    void deleteTeamById(int id){
        teamRepository.deleteTeamById(id);
    }
}
