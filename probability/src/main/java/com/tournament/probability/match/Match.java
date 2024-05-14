package com.tournament.probability.match;

import com.tournament.probability.team.Team;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

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
    @Nullable
    private int winnerId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hostId")
    private Team hostId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "awayId")
    private Team awayId;
    @Column(name = "host_attempts_on_target")
    private int hostAttemptsOnTarget;
    @Column(name = "away_attempts_on_target")
    private int awayAttemptsOnTarget;
    @Column(name = "host_attempts_off_target")
    private int hostAttemptsOffTarget;
    @Column(name = "away_attempts_off_target")
    private int awayAttemptsOffTarget;
    @Column(name = "host_paraden")
    private int hostParaden;
    @Column(name = "away_paraden")
    private int awayParaden;
    @Column(name = "host_corners")
    private int hostCorners;
    @Column(name = "away_corners")
    private int awayCorners;
    @Column(name = "host_free_kicks")
    private int hostFreeKicks;
    @Column(name = "away_free_kicks")
    private int awayFreeKicks;
    @Column(name = "host_fouls")
    private int hostFouls;
    @Column(name = "away_fouls")
    private int awayFouls;
    @Column(name = "host_offsides")
    private int hostOffsides;
    @Column(name = "away_offsides")
    private int awayOffsides;

    public Match(int hostTeamGoals, int awayTeamGoals, int winnerId, Team hostId, Team awayId) {
        this.hostTeamGoals = hostTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.winnerId = winnerId;
        this.hostId = hostId;
        this.awayId = awayId;
    }

    Match() {
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

    public int getHostTotalAttempts() {return hostAttemptsOnTarget;}

    public void setHostTotalAttempts(int hostAttemptsOnTarget) {this.hostAttemptsOnTarget = hostAttemptsOnTarget;}

    public int getAwayTotalAttempts() {return awayAttemptsOnTarget;}

    public void setAwayTotalAttempts(int awayAttemptsOnTarget) {this.awayAttemptsOnTarget = awayAttemptsOnTarget;}

    public int getHostAttemptsOffTarget() {return hostAttemptsOffTarget;}

    public void setHostAttemptsOffTarget(int hostAttemptsOffTarget) {this.hostAttemptsOffTarget = hostAttemptsOffTarget;}

    public int getAwayAttemptsOffTarget() {return awayAttemptsOffTarget;}

    public void setAwayAttemptsOffTarget(int awayAttemptsOffTarget) {this.awayAttemptsOffTarget = awayAttemptsOffTarget;}

    public int getHostParaden() {return hostParaden;}

    public void setHostParaden(int hostParaden) {this.hostParaden = hostParaden;}

    public int getAwayParaden() {return awayParaden;}

    public void setAwayParaden(int awayParaden) {this.awayParaden = awayParaden;}

    public int getHostCorners() {return hostCorners;}

    public void setHostCorners(int hostCorners) {this.hostCorners = hostCorners;}

    public int getAwayCorners() {return awayCorners;}

    public void setAwayCorners(int awayCorners) {this.awayCorners = awayCorners;}

    public int getHostFreeKicks() {return hostFreeKicks;}

    public void setHostFreeKicks(int hostFreeKicks) {this.hostFreeKicks = hostFreeKicks;}

    public int getAwayFreeKicks() {return awayFreeKicks;}

    public void setAwayFreeKicks(int awayFreeKicks) {this.awayFreeKicks = awayFreeKicks;}

    public int getHostFouls() {return hostFouls;}

    public void setHostFouls(int hostFouls) {this.hostFouls = hostFouls;}

    public int getAwayFouls() {return awayFouls;}

    public void setAwayFouls(int awayFouls) {this.awayFouls = awayFouls;}

    public int getHostOffsides() {return hostOffsides;}

    public void setHostOffsides(int hostOffsides) {this.hostOffsides = hostOffsides;}

    public int getAwayOffsides() {return awayOffsides;}

    public void setAwayOffsides(int awayOffsides) {this.awayOffsides = awayOffsides;}
}