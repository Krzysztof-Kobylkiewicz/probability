package com.tournament.probability.tournamentType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TournamentTypeRepositoryTest {

    @Autowired
    private TournamentTypeRepository repository;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldFindTournamentTypeByType() {

        //given
        TournamentType tournamentType = new TournamentType('T', "Description", 8, 64);
        repository.save(tournamentType);

        //when
        boolean isTournamentTypeFound = repository.findByType(tournamentType.getTournamentType()).isPresent();

        //then
        Assertions.assertTrue(isTournamentTypeFound);
    }

    @Test
    void shouldNotFindTournamentTypeByType(){
        //given
        char type = 'T';

        //when
        boolean isTournamentTypeFound = repository.findByType(type).isPresent();

        //then
        Assertions.assertFalse(isTournamentTypeFound);
    }
}