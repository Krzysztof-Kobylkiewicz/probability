package com.tournament.probability.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/ranking")
public class PostRankingController {
    private final AddRanking addRanking;
    @Autowired
    PostRankingController (AddRanking addRanking){
        this.addRanking = addRanking;}
    @PostMapping(path = "/add")
    public ResponseEntity<Ranking> postRankingMethod (@RequestBody Ranking ranking){
        Ranking createdRanking = addRanking.postRankingMethod(ranking);
        return new ResponseEntity<Ranking>(createdRanking, HttpStatus.CREATED);
    }
}