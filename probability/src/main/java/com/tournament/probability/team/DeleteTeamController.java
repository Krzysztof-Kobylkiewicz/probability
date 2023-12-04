package com.tournament.probability.team;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/team")
@Transactional
public class DeleteTeamController {
    private final DeleteTeam deleteTeam;
    @Autowired
    public DeleteTeamController(DeleteTeam deleteTeam) {
        this.deleteTeam = deleteTeam;
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTeamById(@PathVariable("id") int id){
        deleteTeam.deleteTeamById(id);
    }
}
