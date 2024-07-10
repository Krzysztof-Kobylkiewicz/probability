package com.tournament.probability.team;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository repository;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldFindTeamByName() {
        //given
        Team team = new Team("testName", "testOwner", "test@gmail.com");
        repository.save(team);

        //when
        boolean isTeamPresent = repository.findTeamByName("testName").isPresent();

        //then
        Assertions.assertTrue(isTeamPresent);

    }

    @Test
    void shouldNotFindByName(){
        //given
        String name = "nameNotPresent";

        //when
        boolean isTeamPresent = repository.findTeamByName(name).isPresent();

        //then
        Assertions.assertFalse(isTeamPresent);
    }
}