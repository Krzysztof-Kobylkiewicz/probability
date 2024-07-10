package com.tournament.probability.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    Optional<Team> findTeamByName(String team_name);

    void deleteTeamById(int id);

    @Query("SELECT t FROM Team t WHERE t.id = ?1")
    Optional<Team> findTeamById(long id);

}
