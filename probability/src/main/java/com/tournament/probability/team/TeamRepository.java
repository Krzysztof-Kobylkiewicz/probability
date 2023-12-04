package com.tournament.probability.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface TeamRepository extends JpaRepository<Team, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM team WHERE team_name =?1")
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    Optional<Team> findTeamByName(String team_name);

    void deleteTeamById(int id);

    Team getTeamByName(String name);
}
