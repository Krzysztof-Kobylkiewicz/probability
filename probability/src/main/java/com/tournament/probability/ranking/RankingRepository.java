package com.tournament.probability.ranking;

import com.tournament.probability.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("SELECT r FROM Ranking r WHERE r.id = ?1")
    Optional<Ranking> findRankingById(int id);

    void deleteRankingById(int id);
}
