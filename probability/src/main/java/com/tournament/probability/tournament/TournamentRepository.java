package com.tournament.probability.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    @Query("SELECT t FROM Tournament t WHERE t.name = ?1")
    Optional<Tournament> findByName(String name);
}
