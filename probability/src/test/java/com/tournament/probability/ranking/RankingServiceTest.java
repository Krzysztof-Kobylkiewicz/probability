package com.tournament.probability.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(rankingRepository.save(any(Ranking.class))).thenReturn(mockRanking);
    }

    @Test
    void itShouldAddRankingAndSave() {
        Ranking result = rankingService.addRanking(mockRanking);

        verify(rankingRepository).save(mockRanking);
        assertEquals(mockRanking, result);
    }
}