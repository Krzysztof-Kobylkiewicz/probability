package com.tournament.probability.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
class GetMatchController {
    private final GetSpecificMatch getSpecificMatch;
    @Autowired
    GetMatchController(GetSpecificMatch getSpecificMatch){
        this.getSpecificMatch = getSpecificMatch;
    }
    @GetMapping(path = "/getMatchById")
    public Match getMatchByIdMethod(@RequestParam int id){
        return getSpecificMatch.getMatchByIdMethod(id);
    }
}
