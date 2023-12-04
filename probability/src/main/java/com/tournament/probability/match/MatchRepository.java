package com.tournament.probability.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("SELECT m FROM Team m WHERE m.name = ?1")
    Optional<Match> findMatchByTeamName (String name);
}
