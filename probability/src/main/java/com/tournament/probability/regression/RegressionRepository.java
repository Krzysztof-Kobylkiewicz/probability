package com.tournament.probability.regression;

import com.tournament.probability.match.Match;
import com.tournament.probability.ranking.Ranking;
import com.tournament.probability.regression.logistic.MatchAvgDataDTO;
import com.tournament.probability.tournamentType.TournamentType;
import com.tournament.probability.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegressionRepository extends JpaRepository<Match, Integer> {

    @Query("SELECT tt FROM TournamentType tt WHERE tt.id = ?1")
    TournamentType findTypeById(int id);

    @Query("SELECT t FROM Team t")
    List<Team> findAllTeams();

    @Query("SELECT new com.tournament.probability.regression.MatchRankingDTO(" +
            " m.hostTeamGoals as hostTeamGoals, m.awayTeamGoals as awayTeamGoals, m.winnerId as winnerId, m.hostAttemptsOnTarget as hostAttemptsOnTarget, m.awayAttemptsOnTarget as awayAttemptsOnTarget, m.hostAttemptsOffTarget as hostAttemptsOffTarget, m.awayAttemptsOffTarget as awayAttemptsOffTarget, " +
            " m.hostParaden as hostParaden, m.awayParaden as awayParaden, m.hostCorners as hostCorners, m.awayCorners as awayCorners, m.hostFreeKicks as hostFreeKicks, m.awayFreeKicks as awayFreeKicks, m.hostFouls as hostFouls, m.awayFouls as awayFouls, m.hostOffsides as hostOffsides, m.awayOffsides as awayOffsides, " +
            " ht.id as hostId, at.id as awayId, " +
            " hr.averageAge as hostAverageAge, hr.teamValue as hostTeamValue, hr.numberOfPlayers as hostNumberOfPlayers, hr.confederation as hostConfederation, hr.points as hostPoints, hr.position as hostPosition, hr.rankingDate as hostRankingDate, " +
            " ar.averageAge as awayAverageAge, ar.teamValue as awayTeamValue, ar.numberOfPlayers as awayNumberOfPlayers, ar.confederation as awayConfederation, ar.points as awayPoints, ar.position as awayPosition, ar.rankingDate as awayRankingDate, " +
            " tou.id as id, tou.name as name) " +
            " FROM Match m" +
            " LEFT JOIN m.tournamentId tou" +
            " LEFT JOIN m.hostId ht" +
            " LEFT JOIN m.awayId at" +
            " LEFT JOIN ht.rankings hr " +
            " LEFT JOIN at.rankings ar " +
            " WHERE hr.rankingDate = (SELECT MIN(r1.rankingDate) " +
            "                       FROM Ranking r1 " +
            "                       WHERE r1.teamId.id = ht.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r1.rankingDate) as string)" +
            "                       )" +
            " AND ar.rankingDate = (SELECT MIN(r2.rankingDate) " +
            "                       FROM Ranking r2 " +
            "                       WHERE r2.teamId.id = at.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r2.rankingDate) as string)" +
            "                       )" +
            " AND m.hostAttemptsOnTarget IS NOT NULL " +
            " AND m.awayAttemptsOnTarget IS NOT NULL")
    List<MatchRankingDTO> findDataToModel();

    @Query("SELECT new com.tournament.probability.regression.MatchRankingDTO(" +
            " m.hostTeamGoals as hostTeamGoals, m.awayTeamGoals as awayTeamGoals, m.winnerId as winnerId, m.hostAttemptsOnTarget as hostAttemptsOnTarget, m.awayAttemptsOnTarget as awayAttemptsOnTarget, m.hostAttemptsOffTarget as hostAttemptsOffTarget, m.awayAttemptsOffTarget as awayAttemptsOffTarget, " +
            " m.hostParaden as hostParaden, m.awayParaden as awayParaden, m.hostCorners as hostCorners, m.awayCorners as awayCorners, m.hostFreeKicks as hostFreeKicks, m.awayFreeKicks as awayFreeKicks, m.hostFouls as hostFouls, m.awayFouls as awayFouls, m.hostOffsides as hostOffsides, m.awayOffsides as awayOffsides, " +
            " ht.id as hostId, at.id as awayId, " +
            " hr.averageAge as hostAverageAge, hr.teamValue as hostTeamValue, hr.numberOfPlayers as hostNumberOfPlayers, hr.confederation as hostConfederation, hr.points as hostPoints, hr.position as hostPosition, hr.rankingDate as hostRankingDate, " +
            " ar.averageAge as awayAverageAge, ar.teamValue as awayTeamValue, ar.numberOfPlayers as awayNumberOfPlayers, ar.confederation as awayConfederation, ar.points as awayPoints, ar.position as awayPosition, ar.rankingDate as awayRankingDate, " +
            " tou.id as id, tou.name as name) " +
            " FROM Match m" +
            " LEFT JOIN m.tournamentId tou" +
            " LEFT JOIN m.hostId ht" +
            " LEFT JOIN m.awayId at" +
            " LEFT JOIN ht.rankings hr " +
            " LEFT JOIN at.rankings ar " +
            " WHERE hr.rankingDate = (SELECT MIN(r1.rankingDate) " +
            "                       FROM Ranking r1 " +
            "                       WHERE r1.teamId.id = ht.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r1.rankingDate) as string)" +
            "                       )" +
            " AND ar.rankingDate = (SELECT MIN(r2.rankingDate) " +
            "                       FROM Ranking r2 " +
            "                       WHERE r2.teamId.id = at.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r2.rankingDate) as string)" +
            "                       )" +
            " AND m.hostAttemptsOnTarget IS NOT NULL " +
            " AND m.awayAttemptsOnTarget IS NOT NULL" +
            " AND ht.id = ?1")
    List<MatchRankingDTO> findDataToModelAboutSpecificTeam(int id);

    @Query("SELECT new com.tournament.probability.regression.logistic.MatchAvgDataDTO(" +
            /*" avg(m.hostTeamGoals) as hostTeamGoals, avg(m.awayTeamGoals) as awayTeamGoals, " +*/
            " avg(m.hostAttemptsOnTarget) as hostAttemptsOnTarget, avg(m.awayAttemptsOnTarget) as awayAttemptsOnTarget, avg(m.hostAttemptsOffTarget) as hostAttemptsOffTarget, avg(m.awayAttemptsOffTarget) as awayAttemptsOffTarget, " +
            " avg(m.hostParaden) as hostParaden, avg(m.awayParaden) as awayParaden, avg(m.hostCorners) as hostCorners, avg(m.awayCorners) as awayCorners, avg(m.hostFreeKicks) as hostFreeKicks, avg(m.awayFreeKicks) as awayFreeKicks, avg(m.hostFouls) as hostFouls, avg(m.awayFouls) as awayFouls, avg(m.hostOffsides) as hostOffsides, avg(m.awayOffsides) as awayOffsides " +
//            " avg(hr.points) as hostPoints, avg(hr.position) as hostPosition, " +
//            " avg(ar.points) as awayPoints, avg(ar.position) as awayPosition " +
            " ) " +
            " FROM Match m" +
            " LEFT JOIN m.tournamentId tou" +
            " LEFT JOIN m.hostId ht" +
            " LEFT JOIN m.awayId at" +
            " LEFT JOIN ht.rankings hr " +
            " LEFT JOIN at.rankings ar " +
            " WHERE hr.rankingDate = (SELECT MIN(r1.rankingDate) " +
            "                       FROM Ranking r1 " +
            "                       WHERE r1.teamId.id = ht.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r1.rankingDate) as string)" +
            "                       )" +
            " AND ar.rankingDate = (SELECT MIN(r2.rankingDate) " +
            "                       FROM Ranking r2 " +
            "                       WHERE r2.teamId.id = at.id " +
            "                       AND substring(tou.name, length(tou.name) - 3) = cast(year(r2.rankingDate) as string)" +
            "                       )" +
            " AND m.hostAttemptsOnTarget IS NOT NULL " +
            " AND m.awayAttemptsOnTarget IS NOT NULL" +
            " AND ht.name = ?1")
    Optional<MatchAvgDataDTO> findDataAboutSpecificTeamByName(String name);

    @Query(" SELECT r " +
           " FROM Team t " +
           " LEFT JOIN t.rankings r " +
           " WHERE t.name = ?1 " +
           " AND r.numberOfPlayers IS NOT NULL ")
    Ranking findRankingDataByName(String name);

}
