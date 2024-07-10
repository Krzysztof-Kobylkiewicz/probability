package com.tournament.probability.match;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    private Match mockMatch;

    @BeforeEach
    void setUp(){
        mockMatch = new Match();
        mockMatch.setHostTeamGoals(4);
        mockMatch.setId(1);
        matchRepository.save(mockMatch);
    }

    @Test
    void itShouldCreateMatchAndSave() {
        when(matchRepository.save(ArgumentMatchers.any(Match.class))).thenReturn(mockMatch);
        Match created = matchService.createMatch(mockMatch);

        Assertions.assertThat(created.getHostTeamGoals()).isSameAs(mockMatch.getHostTeamGoals());
        verify(matchRepository, times(2)).save(mockMatch);
    }

    @Test
    void itShouldGetMatchById(){
        when(matchRepository.findById(anyInt())).thenReturn(Optional.of(mockMatch));
        Optional<Match> result = matchService.getMatchById(1);

        verify(matchRepository).findById(1);
        assertEquals(Optional.of(mockMatch), result);

    }

    @Test
    void itShouldUpdateMatchAndSave(){
        when(matchRepository.save(ArgumentMatchers.any(Match.class))).thenReturn(mockMatch);
        when(matchRepository.findById(anyInt())).thenReturn(Optional.of(mockMatch));
        Match result = matchService.updateMatch(1, mockMatch);

        verify(matchRepository).findById(1);
        verify(matchRepository, times(2)).save(mockMatch);
        assertEquals(mockMatch, result);
    }
}