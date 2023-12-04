package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ttype")
public class PutTournamentTypeController {
    private final UpdateTournamentType updateTournamentType;
    @Autowired
    PutTournamentTypeController(UpdateTournamentType updateTournamentType){
        this.updateTournamentType = updateTournamentType;
    }
    @PutMapping(path = "/update")
    public ResponseEntity<TournamentType> updateTournamentTypeMethod(
            @RequestBody TournamentType tournamentType){
        TournamentType tournamentTypeUpdated = updateTournamentType.updateTournamentTypeMethod(tournamentType);
        return new ResponseEntity<>(tournamentTypeUpdated ,HttpStatus.OK);
    }
//    @PutMapping(path = "/update/byid")
//    public ResponseEntity<TournamentType> updateTournamentTypeById(
//            @RequestBody TournamentType tournamentType){
//        TournamentType tournamentTypeUpdated = updateTournamentType.updateTournamentTypeMethod(tournamentType);
//        return new ResponseEntity<>(tournamentTypeUpdated ,HttpStatus.OK);
//    }
}
