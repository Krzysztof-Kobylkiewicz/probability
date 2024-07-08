package com.tournament.probability.tournament;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tournament.probability.match.Match;
import com.tournament.probability.tournamentType.TournamentType;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @SequenceGenerator(name = "tournamentIdSequence",
            sequenceName = "tournamentIdSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tournamentIdSequence")
    @Column(name = "tournament_id")
    private int id;
    @Column(name = "tournament_name")
    private String name;
    @Column(name = "teams_participating")
    private Integer teamsParticipating;
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @Column(name = "winner")
    private String winner;
    @OneToMany(mappedBy = "tournamentId")
    private List<Match> matches;

    @ManyToOne(cascade = CascadeType.MERGE)
    private TournamentType tournamentTypeId;

    public Tournament(int id, String name, Integer teamsParticipating,
                      Date startDate, Date endDate, String winner) {
        this.id = id;
        this.name = name;
        this.teamsParticipating = teamsParticipating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winner = winner;
    }

    public Tournament() {
    }

    public Tournament(int id, String name, int teamsParticipating) {
        this.id = id;
        this.name = name;
        this.teamsParticipating = teamsParticipating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamsParticipating() {
        return teamsParticipating;
    }

    public void setTeamsParticipating(Integer teamsParticipating) {
        this.teamsParticipating = teamsParticipating;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public TournamentType getTournamentTypeId() {
        return tournamentTypeId;
    }

    public void setTournamentTypeId(TournamentType tournamentTypeId) {
        this.tournamentTypeId = tournamentTypeId;
    }
}