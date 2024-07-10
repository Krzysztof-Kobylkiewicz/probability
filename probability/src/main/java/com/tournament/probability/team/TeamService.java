package com.tournament.probability.team;

import com.tournament.probability.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    Optional<Team> getTeamByName(String name){
        Optional<Team> optionalTeam = teamRepository.findTeamByName(name);
        if(optionalTeam.isEmpty()){
            throw new ApiRequestException("Team '" + name + "' does not exist.");
        } else {
            return optionalTeam;
        }
    }
    Team addTeam(Team team){
        Optional<Team> teamOptional = teamRepository.findTeamByName(team.getName());
        if(teamOptional.isPresent()){
            throw new IllegalStateException("Team with given name already exist. Please provide different Team name.");
        }
        return teamRepository.save(team);
    }
    void deleteTeamById(int id){
        teamRepository.deleteTeamById(id);
    }
}