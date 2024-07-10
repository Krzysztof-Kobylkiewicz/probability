package com.tournament.probability.match;

import com.tournament.probability.team.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MatchRepositoryTest {

    @Autowired
    private MatchRepository repository;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldFindMatchById() {
        //given
        Match match = new Match(2,2,2,new Team(),new Team());
        repository.save(match);

        //when
        boolean matchToFind = repository.findById(match.getId()).isPresent();

        //then
        assertTrue(matchToFind);
    }
}