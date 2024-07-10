package com.tournament.probability.tournament;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository repository;

    @Test
    void shouldFindTournamentByName() {
        //given
        Tournament tournament = new Tournament("TEST TOURNAMENT", 32, "Test winner");
        repository.save(tournament);

        //when
        boolean isTournamentPresent = repository.findByName(tournament.getName()).isPresent();

        //then
        Assertions.assertTrue(isTournamentPresent);
    }

    @Test
    void shouldNotFindTournamentByName(){
        //given
        String name = "fakeTournamentName";

        //when
        boolean isTournamentPresent = repository.findByName(name).isPresent();

        //then
        Assertions.assertFalse(isTournamentPresent);
    }
}