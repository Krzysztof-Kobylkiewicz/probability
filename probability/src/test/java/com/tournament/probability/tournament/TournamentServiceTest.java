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

    @Test
    void itShouldGetTournamentByItsIdAndReturnId(){
        when(tournamentRepository.findTournamentById(anyInt())).thenReturn(Optional.of(mockTournament));

        Tournament result = tournamentService.getTournamentById(anyInt());

        verify(tournamentRepository, times(1)).findTournamentById(anyInt());
        assertEquals(mockTournament, result);
    }

    @Test
    void itShouldUpdateTournamentByItsIdAndReturnIt(){
        when(tournamentRepository.findTournamentById(mockTournament.getId()))
                .thenReturn(Optional.of(mockTournament));
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(mockTournament);

        Tournament result = tournamentService.updateTournamentById(mockTournament.getId(), mockTournament);

        verify(tournamentRepository, times(1)).findTournamentById(mockTournament.getId());
        verify(tournamentRepository, times(1)).save(mockTournament);
        assertEquals(mockTournament, result);
    }

    @Test
    void itShouldDeleteTournamentByItsId(){

        tournamentService.deleteTournamentById(anyInt());

        verify(tournamentRepository).deleteTournamentById(anyInt());
    }
}