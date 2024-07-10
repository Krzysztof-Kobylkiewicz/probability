package com.tournament.probability.tournamentType;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/type")
public class TournamentTypeController {
    @Autowired
    private final TournamentTypeService tournamentTypeService;

    TournamentTypeController(TournamentTypeService tournamentTypeService){
        this.tournamentTypeService = tournamentTypeService;
    }
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<TournamentType> getTournamentTypeByIdMethod(@PathVariable("id") int id){
        TournamentType newTournamentType = tournamentTypeService.getTournamentTypeById(id);
        return new ResponseEntity<>(newTournamentType, HttpStatus.OK);
    }
    @PostMapping(path = "/add")
    public ResponseEntity<TournamentType> AddTournamentTypeMethod(
            @RequestBody TournamentType tournamentType){
        TournamentType newTournamentType = tournamentTypeService.addTournamentType(tournamentType);
        return new ResponseEntity<>(newTournamentType, HttpStatus.CREATED);
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<TournamentType> updateTournamentTypeMethod(@RequestBody TournamentType tournamentType, @PathVariable int id){
        TournamentType tournamentTypeUpdated = tournamentTypeService.updateTournamentType(tournamentType, id);
        return new ResponseEntity<>(tournamentTypeUpdated ,HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<TournamentType> deleteTournamentType(@PathVariable("id") int id){
        tournamentTypeService.deleteTournamentType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}