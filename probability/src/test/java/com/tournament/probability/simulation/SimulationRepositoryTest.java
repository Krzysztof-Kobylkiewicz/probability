package com.tournament.probability.simulation;

import com.tournament.probability.team.Team;
import com.tournament.probability.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SimulationRepositoryTest {

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void itShouldSelectTeamByItsName() {
        //given
        Team team = new Team("test", "owner", "email@gmail.com");
        teamRepository.save(team);

        //when
        Team selectedTeam = simulationRepository.findTeamByTeamName(team.getName());

        //then
        assertEquals(selectedTeam.getName(), team.getName());
    }
}