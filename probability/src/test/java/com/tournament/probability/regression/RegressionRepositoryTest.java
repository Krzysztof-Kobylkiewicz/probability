package com.tournament.probability.regression;

import com.tournament.probability.match.Match;
import com.tournament.probability.match.MatchRepository;
import com.tournament.probability.ranking.Ranking;
import com.tournament.probability.ranking.RankingRepository;
import com.tournament.probability.regression.logistic.MatchAvgDataDTO;
import com.tournament.probability.team.Team;
import com.tournament.probability.team.TeamRepository;
import com.tournament.probability.tournament.Tournament;
import com.tournament.probability.tournament.TournamentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RegressionRepositoryTest {

    @Autowired
    private RegressionRepository regressionRepository;

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    private Team team1;

    @BeforeEach
    void setUp(){

        //given
        team1 = new Team("team1", "owner1", "email1@gmail.com");
        Team team2 = new Team("team2", "owner2", "email2@gmail.com");
        teamRepository.save(team1);
        teamRepository.save(team2);

        Tournament tournament = new Tournament("World Cup 2022", 32, "Niemcy");
        tournamentRepository.save(tournament);

        Match match1 = new Match(3, 1, team1.getId(), team1, team2, 2,2,2,2,2,2,2,2,2,2,2,2,2,2, tournament);
        Match match2 = new Match(4, 3, team1.getId(), team1, team2, 3,3,3,3,3,3,3,3,3,3,3,3,3,3, tournament);
        matchRepository.save(match1);
        matchRepository.save(match2);

        Ranking ranking1 = new Ranking(24.31f, 123.23f, 24, "CONFEDERATION", 1456, 45, LocalDate.of(2022, 6, 6), team1);
        Ranking ranking2 = new Ranking(27.13f, 101.98f, 28, "CONFEDERATION2", 1673, 3, LocalDate.of(2022, 7, 7), team2);
        rankingRepository.save(ranking1);
        rankingRepository.save(ranking2);
    }

    @AfterEach
    void tearDown(){
        regressionRepository.deleteAll();
    }

    @Test
    void itShouldSelectListOfMatchRankingDTO() {

        //when
        List<MatchRankingDTO> matchRankingDTOs = regressionRepository.findDataToModel();

        int year = matchRankingDTOs.get(0).getHostRankingDate().getYear();
        String tournamentYearStr = matchRankingDTOs.get(0).getName().substring(matchRankingDTOs.get(0).getName().length() - 4);
        int tournamentYear = Integer.parseInt(tournamentYearStr);

        //then
        Assertions.assertThat(matchRankingDTOs).hasSize(2);
        Assertions.assertThat(matchRankingDTOs).extracting(MatchRankingDTO::getHostTeamGoals).
                containsExactlyInAnyOrder(3,4);
        assertEquals(year, tournamentYear);
    }

    @Test
    void itShouldSelectMatchRankingDTOListAboutExactlyOneTeamByItsId(){

        //when
        List<MatchRankingDTO> matchRankingDTOs = regressionRepository.findDataToModelAboutSpecificTeam(team1.getId());

        List<Integer> ids = new ArrayList<>();
        for (MatchRankingDTO matchRankingDTO : matchRankingDTOs){
            ids.add(matchRankingDTO.getHostId());
        }

        //then
        for (Integer id : ids){
            org.junit.jupiter.api.Assertions.assertEquals(team1.getId(), id);
        }
    }

    @Test
    void itShouldSelectNotNullMatchAvgDataDTO() {

        //when
        boolean isMatchAvgDataDTOPresent = regressionRepository.findDataAboutSpecificTeamByName(team1.getName()).isPresent();

        //then
        org.junit.jupiter.api.Assertions.assertTrue(isMatchAvgDataDTOPresent);
        assertNotNull(regressionRepository.findDataAboutSpecificTeamByName(team1.getName()).get().getHostFreeKicks());
    }

    @Test
    void itShouldSelectNullMatchAvgDataDTO(){

        //given
        String name = "randomName";

        //when
        Optional<MatchAvgDataDTO> avgDataDTO = regressionRepository.findDataAboutSpecificTeamByName(name);

        //then
        assertNull(avgDataDTO.get().getAwayAttemptsOnTarget());
    }
}