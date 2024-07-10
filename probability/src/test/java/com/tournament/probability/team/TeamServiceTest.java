package com.tournament.probability.team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team mockTeam1;
    private Team mockTeam2;

    private List<Team> teamList;

    @BeforeEach
    void setUp() {
        mockTeam1 = new Team();
        mockTeam1.setId(1);
        mockTeam1.setName("test1");

        mockTeam2 = new Team();
        mockTeam2.setId(2);
        mockTeam2.setName("test2");

        teamList = Arrays.asList(mockTeam1, mockTeam2);
    }

    @Test
    void itShouldGetAllTeams() {
        when(teamRepository.findAll()).thenReturn(teamList);
        List<Team> result = teamService.getAllTeams();

        verify(teamRepository).findAll();
        assertEquals(teamList, result);
    }

    @Test
    void itShouldGetTeamByName() {
        when(teamRepository.findTeamByName(mockTeam1.getName())).thenReturn(Optional.of(mockTeam1));
        Optional<Team> result = teamService.getTeamByName(mockTeam1.getName());

        verify(teamRepository).findTeamByName(mockTeam1.getName());
        assertEquals(Optional.of(mockTeam1), result);
    }

    @Test
    void itShouldAddTeamAndSave() {
        when(teamRepository.findTeamByName(mockTeam1.getName())).thenReturn(Optional.empty());
        when(teamRepository.save(any(Team.class))).thenReturn(mockTeam1);
        Team result = teamService.addTeam(mockTeam1);

        verify(teamRepository).findTeamByName(mockTeam1.getName());
        verify(teamRepository).save(mockTeam1);
        assertEquals(mockTeam1, result);
    }

    @Test
    void itShouldNotAddTeam(){
        when(teamRepository.findTeamByName(mockTeam1.getName())).thenReturn(Optional.of(mockTeam1));

        verify(teamRepository, times(0)).save(mockTeam1);
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> {teamService.addTeam(mockTeam1);
        });
    }

    @Test
    void itShouldDeleteTeamById() {

        teamService.deleteTeamById(mockTeam1.getId());

        verify(teamRepository, times(1)).deleteTeamById(mockTeam1.getId());
    }
}