package com.tournament.probability.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.id = ?1")
    Optional<Match> findById (int id);

    void deleteMatchById(int id);

}
