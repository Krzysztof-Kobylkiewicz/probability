package com.tournament.probability.ranking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tournament.probability.team.Team;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table
public class Ranking {
    @Id
    @SequenceGenerator(name = "rankingSequence", sequenceName = "rankingSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rankingSequence")
    private long id;
    @JoinColumn( name = "teamId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team teamId;

    private Float averageAge;

    private Float teamValue;

    private Integer numberOfPlayers;

    private String confederation;

    private Integer points;

    private Integer position;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rankingDate;

    public Ranking (Float averageAge, Float teamValue, Integer numberOfPlayers, String confederation,
                    Integer points, Integer position, Date rankingDate){
        this.averageAge = averageAge;
        this.teamValue = teamValue;
        this.numberOfPlayers = numberOfPlayers;
        this.confederation = confederation;
        this.points = points;
        this.position = position;
        this.rankingDate = rankingDate;
    }

    public Ranking(String confederation, Integer points, Integer position) {
        this.confederation = confederation;
        this.points = points;
        this.position = position;
    }

    public Ranking(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public Float getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(Float averageAge) {
        this.averageAge = averageAge;
    }


    public Float getTeamValue() {
        return teamValue;
    }

    public void setTeamValue(Float teamValue) {
        this.teamValue = teamValue;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        String CONFEDERATION = confederation.toUpperCase();
        if (CONFEDERATION.equals("UEFA") || CONFEDERATION.equals("CONMEBOL") || CONFEDERATION.equals("CONCACAF") ||
        CONFEDERATION.equals("CAF") || CONFEDERATION.equals("AFC") || CONFEDERATION.equals("OFC")){
            this.confederation = confederation;
        } else {
            throw new IllegalArgumentException(confederation + " is not a valid confederation.");
        }
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Date getRankingDate() {
        return rankingDate;
    }

    public void setRankingDate(Date rankingDate) {
        this.rankingDate = rankingDate;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", averageAge=" + averageAge +
                ", teamValue=" + teamValue +
                ", numberOfPlayers=" + numberOfPlayers +
                ", confederation='" + confederation + '\'' +
                ", points=" + points +
                ", position=" + position +
                ", rankingDate=" + rankingDate +
                '}';
    }
}