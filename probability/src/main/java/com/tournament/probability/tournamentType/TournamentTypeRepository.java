package com.tournament.probability.tournamentType;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface TournamentTypeRepository extends JpaRepository<TournamentType, Integer> {
    @Query("SELECT tt FROM TournamentType tt WHERE tt.id =?1")
    Optional<TournamentType> findTournamentTypeById(int id);

    @Query("SELECT dd FROM TournamentType dd WHERE dd.tournamentType =?1")
    Optional<TournamentType> findByType(Character type);

    void deleteTournamentTypeById(int id);

}
