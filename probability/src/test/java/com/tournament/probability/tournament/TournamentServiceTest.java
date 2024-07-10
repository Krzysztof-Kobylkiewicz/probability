package com.tournament.probability.tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    private Tournament mockTournament;

    @BeforeEach
    void setUp(){
        mockTournament = new Tournament();
        mockTournament.setId(1);
        mockTournament.setName("testTournament");
    }

    @Test
    void itShouldAddTournament() {
        when(tournamentRepository.findByName(mockTournament.getName())).thenReturn(Optional.empty());
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(mockTournament);

        Tournament result = tournamentService.addTournament(mockTournament);

        verify(tournamentRepository, times(1)).findByName(mockTournament.getName());
        verify(tournamentRepository, times(1)).save(mockTournament);
        assertEquals(mockTournament, result);
    }
}