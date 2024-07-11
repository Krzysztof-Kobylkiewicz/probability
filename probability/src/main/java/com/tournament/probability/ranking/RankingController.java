package com.tournament.probability.ranking;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/ranking")
public class RankingController {
    private final RankingService rankingService;
    @Autowired
    RankingController (RankingService rankingService){
        this.rankingService = rankingService;}

    @PostMapping(path = "/add")
    public ResponseEntity<Ranking> addRanking (@RequestBody Ranking ranking){
        Ranking createdRanking = rankingService.addRanking(ranking);
        return new ResponseEntity<>(createdRanking, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Ranking> getRankingById (@PathVariable int id){
        Ranking returnedRanking = rankingService.getRankingById(id);
        return new ResponseEntity<>(returnedRanking, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Ranking> updateRankingById(@PathVariable int id, @RequestBody Ranking ranking){
        Ranking updatedRanking = rankingService.updateRankingById(id, ranking);
        return new ResponseEntity<>(updatedRanking, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    public ResponseEntity<Ranking> deleteRankingById(@PathVariable int id){
        rankingService.deleteRankingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}