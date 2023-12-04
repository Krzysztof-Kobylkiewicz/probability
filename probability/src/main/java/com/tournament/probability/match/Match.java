package com.tournament.probability.match;

import com.tournament.probability.team.Team;
import com.tournament.probability.tournamentProperties.TournamentProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @SequenceGenerator(name = "matchSequence",
            sequenceName = "matchSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "matchSequence")
    @Column(name = "match_id")
    private int id;
    @Column(name = "host_team_goals")
    @Min(value = 0)
    private int hostTeamGoals;
    @Column(name = "away_team_goals")
    @Min(value = 0)
    private int awayTeamGoals;
    @Column(name = "winner_id")
    private int winnerId;
    @OneToMany(targetEntity = TournamentProperties.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "match_id", nullable = false)
    private List<TournamentProperties> tournamentPropertiesList;

    ///test
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hostId")
    private Team hostId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "awayId")
    private Team awayId;

    public Match(int hostTeamGoals, int awayTeamGoals, int winnerId, Team hostId, Team awayId) {
        this.hostTeamGoals = hostTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.winnerId = winnerId;
        this.hostId = hostId;
        this.awayId = awayId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostTeamGoals() {
        return hostTeamGoals;
    }

    public void setHostTeamGoals(int hostTeamGoals) {
        this.hostTeamGoals = hostTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public Team getHostId() {
        return hostId;
    }

    public void setHostId(Team hostId) {
        this.hostId = hostId;
    }

    public Team getAwayId() {
        return awayId;
    }

    public void setAwayId(Team awayId) {
        this.awayId = awayId;
    }
    /*
    JSON
    {
    "hostTeamGoals" : 2,
    "awayTeamGoals" : 1,
    "winnerId" : 1
    }
    ALBO

    {
    "hostTeamGoals" : 3,
    "awayTeamGoals" : 1,
    "winnerId" : 1,
    "hostId" : {
        "name" : "Borussia Dortmund",
        "owner" : "Kshpichek",
        "email" : "krzysiek12-2000@o2.pl"
    },
    "awayId" : {
        "name" : "Bayern Monachium",
        "owner" : "Fnexar",
        "email" : "ash@o2.pl"
    }
}
     */
}
