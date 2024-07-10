package com.tournament.probability.match;

import com.tournament.probability.team.Team;
import com.tournament.probability.tournament.Tournament;
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
    private long id;
    @Column(name = "host_team_goals")
    @Min(value = 0)
    private int hostTeamGoals;
    @Column(name = "away_team_goals")
    @Min(value = 0)
    private int awayTeamGoals;
    @Column(name = "winner_id")
    private Integer winnerId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hostId")
    private Team hostId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "awayId")
    private Team awayId;
    @Column(name = "host_attempts_on_target")
    private Integer hostAttemptsOnTarget;
    @Column(name = "away_attempts_on_target")
    private Integer awayAttemptsOnTarget;
    @Column(name = "host_attempts_off_target")
    private Integer hostAttemptsOffTarget;
    @Column(name = "away_attempts_off_target")
    private Integer awayAttemptsOffTarget;
    @Column(name = "host_paraden")
    private Integer hostParaden;
    @Column(name = "away_paraden")
    private Integer awayParaden;
    @Column(name = "host_corners")
    private Integer hostCorners;
    @Column(name = "away_corners")
    private Integer awayCorners;
    @Column(name = "host_free_kicks")
    private Integer hostFreeKicks;
    @Column(name = "away_free_kicks")
    private Integer awayFreeKicks;
    @Column(name = "host_fouls")
    private Integer hostFouls;
    @Column(name = "away_fouls")
    private Integer awayFouls;
    @Column(name = "host_offsides")
    private Integer hostOffsides;
    @Column(name = "away_offsides")
    private Integer awayOffsides;

    @JoinColumn(name = "tournament_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Tournament tournamentId;

    public Match(Integer hostTeamGoals, Integer awayTeamGoals, Integer winnerId, Team hostId,
                 Team awayId) {
        this.hostTeamGoals = hostTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.winnerId = winnerId;
        this.hostId = hostId;
        this.awayId = awayId;
    }

    public Match(int hostTeamGoals, int awayTeamGoals, Integer winnerId, Team hostId, Team awayId, Integer hostAttemptsOnTarget, Integer awayAttemptsOnTarget, Integer hostAttemptsOffTarget, Integer awayAttemptsOffTarget, Integer hostParaden, Integer awayParaden, Integer hostCorners, Integer awayCorners, Integer hostFreeKicks, Integer awayFreeKicks, Integer hostFouls, Integer awayFouls, Integer hostOffsides, Integer awayOffsides, Tournament tournamentId) {
        this.hostTeamGoals = hostTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.winnerId = winnerId;
        this.hostId = hostId;
        this.awayId = awayId;
        this.hostAttemptsOnTarget = hostAttemptsOnTarget;
        this.awayAttemptsOnTarget = awayAttemptsOnTarget;
        this.hostAttemptsOffTarget = hostAttemptsOffTarget;
        this.awayAttemptsOffTarget = awayAttemptsOffTarget;
        this.hostParaden = hostParaden;
        this.awayParaden = awayParaden;
        this.hostCorners = hostCorners;
        this.awayCorners = awayCorners;
        this.hostFreeKicks = hostFreeKicks;
        this.awayFreeKicks = awayFreeKicks;
        this.hostFouls = hostFouls;
        this.awayFouls = awayFouls;
        this.hostOffsides = hostOffsides;
        this.awayOffsides = awayOffsides;
        this.tournamentId = tournamentId;
    }

    public Match() {
    }

    public Match(Team hostId) {
        this.hostId = hostId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
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

    public Integer getHostAttemptsOnTarget() {
        return hostAttemptsOnTarget;
    }

    public void setHostAttemptsOnTarget(Integer hostAttemptsOnTarget) {
        this.hostAttemptsOnTarget = hostAttemptsOnTarget;
    }

    public Integer getAwayAttemptsOnTarget() {
        return awayAttemptsOnTarget;
    }

    public void setAwayAttemptsOnTarget(Integer awayAttemptsOnTarget) {
        this.awayAttemptsOnTarget = awayAttemptsOnTarget;
    }

    public Integer getHostAttemptsOffTarget() {
        return hostAttemptsOffTarget;
    }

    public void setHostAttemptsOffTarget(Integer hostAttemptsOffTarget) {
        this.hostAttemptsOffTarget = hostAttemptsOffTarget;
    }

    public Integer getAwayAttemptsOffTarget() {
        return awayAttemptsOffTarget;
    }

    public void setAwayAttemptsOffTarget(Integer awayAttemptsOffTarget) {
        this.awayAttemptsOffTarget = awayAttemptsOffTarget;
    }

    public Integer getHostParaden() {
        return hostParaden;
    }

    public void setHostParaden(Integer hostParaden) {
        this.hostParaden = hostParaden;
    }

    public Integer getAwayParaden() {
        return awayParaden;
    }

    public void setAwayParaden(Integer awayParaden) {
        this.awayParaden = awayParaden;
    }

    public Integer getHostCorners() {
        return hostCorners;
    }

    public void setHostCorners(Integer hostCorners) {
        this.hostCorners = hostCorners;
    }

    public Integer getAwayCorners() {
        return awayCorners;
    }

    public void setAwayCorners(Integer awayCorners) {
        this.awayCorners = awayCorners;
    }

    public Integer getHostFreeKicks() {
        return hostFreeKicks;
    }

    public void setHostFreeKicks(Integer hostFreeKicks) {
        this.hostFreeKicks = hostFreeKicks;
    }

    public Integer getAwayFreeKicks() {
        return awayFreeKicks;
    }

    public void setAwayFreeKicks(Integer awayFreeKicks) {
        this.awayFreeKicks = awayFreeKicks;
    }

    public Integer getHostFouls() {
        return hostFouls;
    }

    public void setHostFouls(Integer hostFouls) {
        this.hostFouls = hostFouls;
    }

    public Integer getAwayFouls() {
        return awayFouls;
    }

    public void setAwayFouls(Integer awayFouls) {
        this.awayFouls = awayFouls;
    }

    public Integer getHostOffsides() {
        return hostOffsides;
    }

    public void setHostOffsides(Integer hostOffsides) {
        this.hostOffsides = hostOffsides;
    }

    public Integer getAwayOffsides() {
        return awayOffsides;
    }

    public void setAwayOffsides(Integer awayOffsides) {
        this.awayOffsides = awayOffsides;
    }

    public Tournament getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Tournament tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", hostTeamGoals=" + hostTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", winnerId=" + winnerId +
                ", hostId=" + hostId +
                ", awayId=" + awayId +
                ", hostAttemptsOnTarget=" + hostAttemptsOnTarget +
                ", awayAttemptsOnTarget=" + awayAttemptsOnTarget +
                ", hostAttemptsOffTarget=" + hostAttemptsOffTarget +
                ", awayAttemptsOffTarget=" + awayAttemptsOffTarget +
                ", hostParaden=" + hostParaden +
                ", awayParaden=" + awayParaden +
                ", hostCorners=" + hostCorners +
                ", awayCorners=" + awayCorners +
                ", hostFreeKicks=" + hostFreeKicks +
                ", awayFreeKicks=" + awayFreeKicks +
                ", hostFouls=" + hostFouls +
                ", awayFouls=" + awayFouls +
                ", hostOffsides=" + hostOffsides +
                ", awayOffsides=" + awayOffsides +
                ", tournamentId=" + tournamentId +
                '}';
    }
}