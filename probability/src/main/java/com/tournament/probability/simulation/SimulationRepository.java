package com.tournament.probability.simulation;

import com.tournament.probability.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationResult, Integer> {

    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    Team findTeamByTeamName(String name);

}
