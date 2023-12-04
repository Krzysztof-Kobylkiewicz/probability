package com.tournament.probability.tournamentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping( path = "/ttype")
class GetTournamentTypeController {
    private final GetTournamentTypeById getTournamentTypeById;

    @Autowired
    GetTournamentTypeController(GetTournamentTypeById getTournamentTypeById){
        this.getTournamentTypeById = getTournamentTypeById;
    }
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Optional<TournamentType>> getTournamentTypeByIdMethod(@PathVariable("id") int id){
        Optional<TournamentType> newOptionalTournamentType = getTournamentTypeById.getTournamentTypeByIdMethod(id);
        return new ResponseEntity<>(newOptionalTournamentType, HttpStatus.OK);
        //return getTournamentTypeById.getTournamentTypeByIdMethod(id);
    }
}
