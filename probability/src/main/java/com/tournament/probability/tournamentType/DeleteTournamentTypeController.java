package com.tournament.probability.tournamentType;

import jakarta.transaction.Transactional;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ttype")
@Transactional
class DeleteTournamentTypeController {
    private final DeleteTournamentType deleteTournamentType;
    @Autowired
    DeleteTournamentTypeController(DeleteTournamentType deleteTournamentType){
        this.deleteTournamentType = deleteTournamentType;
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTournamentTypeById(@PathVariable("id") int id){
        deleteTournamentType.deleteTournamentTypeById(id);
    }
}
