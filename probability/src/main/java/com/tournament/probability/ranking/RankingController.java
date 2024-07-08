package com.tournament.probability.ranking;

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
        return new ResponseEntity<Ranking>(createdRanking, HttpStatus.CREATED);
    }
}