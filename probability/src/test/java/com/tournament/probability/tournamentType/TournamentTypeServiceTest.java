package com.tournament.probability.tournamentType;

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
class TournamentTypeServiceTest {

    @Mock
    private TournamentTypeRepository tournamentTypeRepository;

    @InjectMocks
    private TournamentTypeService tournamentTypeService;

    private TournamentType mockTournamentType;

    @BeforeEach
    void setUp(){
        mockTournamentType = new TournamentType();
        mockTournamentType.setId(1);
        mockTournamentType.setTournamentType('T');
    }

    @Test
    void itShouldGetTournamentTypeById() {
        when(tournamentTypeRepository.findTournamentTypeById(mockTournamentType.getId()))
                .thenReturn(Optional.of(mockTournamentType));
        TournamentType result = tournamentTypeService
                .getTournamentTypeById(mockTournamentType.getId());

        verify(tournamentTypeRepository).findTournamentTypeById(mockTournamentType.getId());
        assertEquals(mockTournamentType, result);
    }

    @Test
    void itShouldAddTournamentTypeAndSave() {
        when(tournamentTypeRepository.findByType(anyChar())).thenReturn(Optional.empty());
        when(tournamentTypeRepository.save(any(TournamentType.class))).thenReturn(mockTournamentType);

        TournamentType result = tournamentTypeService.addTournamentType(mockTournamentType);

        verify(tournamentTypeRepository, times(1)).save(mockTournamentType);
        verify(tournamentTypeRepository, times(1)).findByType(anyChar());
        assertEquals(mockTournamentType, result);

    }

    @Test
    void itShouldUpdateTournamentTypeAndSave() {
        when(tournamentTypeRepository.findTournamentTypeById(anyInt()))
                .thenReturn(Optional.of(mockTournamentType));
        when(tournamentTypeRepository.save(any(TournamentType.class))).thenReturn(mockTournamentType);

        TournamentType result = tournamentTypeService.updateTournamentType(mockTournamentType, anyInt());

        verify(tournamentTypeRepository).findTournamentTypeById(anyInt());
        verify(tournamentTypeRepository, times(1)).save(mockTournamentType);
        assertEquals(mockTournamentType, result);
    }

    @Test
    void itShouldDeleteTournamentType() {

        tournamentTypeRepository.deleteTournamentTypeById(anyInt());

        verify(tournamentTypeRepository).deleteTournamentTypeById(anyInt());
    }
}