package com.tournament.probability.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;

    private Ranking mockRanking;

    @BeforeEach
    void setUp(){
        mockRanking = new Ranking();
        mockRanking.setId(1);
        mockRanking.setPosition(100);
        mockRanking.setConfederation("UEFA");
    }

    @Test
    void itShouldAddRankingAndSave() {
        when(rankingRepository.save(any(Ranking.class))).thenReturn(mockRanking);
        Ranking result = rankingService.addRanking(mockRanking);

        verify(rankingRepository).save(mockRanking);
        assertEquals(mockRanking, result);
    }

    @Test
    void itShouldGetRankingByIdAndReturnIt(){
        when(rankingRepository.findRankingById(anyInt())).thenReturn(Optional.of(mockRanking));
        Ranking result = rankingService.getRankingById(anyInt());

        verify(rankingRepository).findRankingById(anyInt());
        assertEquals(mockRanking, result);
    }

    @Test
    void itShouldUpdateRankingByItsIdAndReturnIt(){
        when(rankingRepository.findRankingById(anyInt())).thenReturn(Optional.of(mockRanking));
        when(rankingRepository.save(any(Ranking.class))).thenReturn(mockRanking);

        Ranking result = rankingService.updateRankingById(anyInt(), mockRanking);

        verify(rankingRepository, times(1)).save(mockRanking);
        verify(rankingRepository).findRankingById(anyInt());
        assertEquals(mockRanking, result);

    }

    @Test
    void itShouldDeleteRankingByItsId(){
        when(rankingRepository.findRankingById(anyInt())).thenReturn(Optional.of(mockRanking));
        rankingRepository.save(mockRanking);

        rankingService.deleteRankingById(1);

        verify(rankingRepository, times(1)).deleteRankingById(1);
    }
}