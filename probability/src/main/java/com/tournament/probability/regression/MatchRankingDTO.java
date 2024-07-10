package com.tournament.probability.regression;

import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;

public class MatchRankingDTO{
        private Integer hostTeamGoals;
        private Integer awayTeamGoals;
        private Integer winnerId;
        private Integer hostAttemptsOnTarget;
        private Integer awayAttemptsOnTarget;
        private Integer hostAttemptsOffTarget;
        private Integer awayAttemptsOffTarget;
        private Integer hostParaden;
        private Integer awayParaden;
        private Integer hostCorners;
        private Integer awayCorners;
        private Integer hostFreeKicks;
        private Integer awayFreeKicks;
        private Integer hostFouls;
        private Integer awayFouls;
        private Integer hostOffsides;
        private Integer awayOffsides;
        private Float hostAverageAge;
        private Float hostTeamValue;
        private Integer hostNumberOfPlayers;
        private String hostConfederation;
        private Integer hostPoints;
        private Integer hostPosition;
        private LocalDate hostRankingDate;
        private Float awayAverageAge;
        private Float awayTeamValue;
        private Integer awayNumberOfPlayers;
        private String awayConfederation;
        private Integer awayPoints;
        private Integer awayPosition;
        private LocalDate awayRankingDate;
        private Integer id;
        private String name;
        private int hostId;
        private int awayId;
        public MatchRankingDTO() {
        }
        public MatchRankingDTO(
                Integer hostTeamGoals, Integer awayTeamGoals, Integer winnerId,
                Integer hostAttemptsOnTarget, Integer awayAttemptsOnTarget,
                Integer hostAttemptsOffTarget, Integer awayAttemptsOffTarget,
                Integer hostParaden, Integer awayParaden, Integer hostCorners,
                Integer awayCorners, Integer hostFreeKicks, Integer awayFreeKicks,
                Integer hostFouls, Integer awayFouls, Integer hostOffsides,
                Integer awayOffsides, Float hostAverageAge, Float hostTeamValue,
                Integer hostNumberOfPlayers, String hostConfederation, Integer hostPoints,
                Integer hostPosition, LocalDate hostRankingDate, Float awayAverageAge,
                Float awayTeamValue, Integer awayNumberOfPlayers, String awayConfederation,
                Integer awayPoints, Integer awayPosition, LocalDate awayRankingDate,
                Integer id, String name, int hostId, int awayId
        ) {
            this.hostTeamGoals = hostTeamGoals;
            this.awayTeamGoals = awayTeamGoals;
            this.winnerId = winnerId;
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
            this.hostAverageAge = hostAverageAge;
            this.hostTeamValue = hostTeamValue;
            this.hostNumberOfPlayers = hostNumberOfPlayers;
            this.hostConfederation = hostConfederation;
            this.hostPoints = hostPoints;
            this.hostPosition = hostPosition;
            this.hostRankingDate = hostRankingDate;
            this.awayAverageAge = awayAverageAge;
            this.awayTeamValue = awayTeamValue;
            this.awayNumberOfPlayers = awayNumberOfPlayers;
            this.awayConfederation = awayConfederation;
            this.awayPoints = awayPoints;
            this.awayPosition = awayPosition;
            this.awayRankingDate = awayRankingDate;
            this.id = id;
            this.name = name;
            this.hostId = hostId;
            this.awayId = awayId;
        }

    public MatchRankingDTO(Integer hostTeamGoals) {
        this.hostTeamGoals = hostTeamGoals;
    }

    public Integer getHostTeamGoals() {
        return hostTeamGoals;
    }

    public void setHostTeamGoals(Integer hostTeamGoals) {
        this.hostTeamGoals = hostTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
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

    public Float getHostAverageAge() {
        return hostAverageAge;
    }

    public void setHostAverageAge(Float hostAverageAge) {
        this.hostAverageAge = hostAverageAge;
    }

    public Float getHostTeamValue() {
        return hostTeamValue;
    }

    public void setHostTeamValue(Float hostTeamValue) {
        this.hostTeamValue = hostTeamValue;
    }

    public Integer getHostNumberOfPlayers() {
        return hostNumberOfPlayers;
    }

    public void setHostNumberOfPlayers(Integer hostNumberOfPlayers) {
        this.hostNumberOfPlayers = hostNumberOfPlayers;
    }

    public String getHostConfederation() {
        return hostConfederation;
    }

    public void setHostConfederation(String hostConfederation) {
        this.hostConfederation = hostConfederation;
    }

    public Integer getHostPoints() {
        return hostPoints;
    }

    public void setHostPoints(Integer hostPoints) {
        this.hostPoints = hostPoints;
    }

    public Integer getHostPosition() {
        return hostPosition;
    }

    public void setHostPosition(Integer hostPosition) {
        this.hostPosition = hostPosition;
    }

    public LocalDate getHostRankingDate() {
        return hostRankingDate;
    }

    public void setHostRankingDate(LocalDate hostRankingDate) {
        this.hostRankingDate = hostRankingDate;
    }

    public Float getAwayAverageAge() {
        return awayAverageAge;
    }

    public void setAwayAverageAge(Float awayAverageAge) {
        this.awayAverageAge = awayAverageAge;
    }

    public Float getAwayTeamValue() {
        return awayTeamValue;
    }

    public void setAwayTeamValue(Float awayTeamValue) {
        this.awayTeamValue = awayTeamValue;
    }

    public Integer getAwayNumberOfPlayers() {
        return awayNumberOfPlayers;
    }

    public void setAwayNumberOfPlayers(Integer awayNumberOfPlayers) {
        this.awayNumberOfPlayers = awayNumberOfPlayers;
    }

    public String getAwayConfederation() {
        return awayConfederation;
    }

    public void setAwayConfederation(String awayConfederation) {
        this.awayConfederation = awayConfederation;
    }

    public Integer getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Integer awayPoints) {
        this.awayPoints = awayPoints;
    }

    public Integer getAwayPosition() {
        return awayPosition;
    }

    public void setAwayPosition(Integer awayPosition) {
        this.awayPosition = awayPosition;
    }

    public LocalDate getAwayRankingDate() {
        return awayRankingDate;
    }

    public void setAwayRankingDate(LocalDate awayRankingDate) {
        this.awayRankingDate = awayRankingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getAwayId() {
        return awayId;
    }

    public void setAwayId(int awayId) {
        this.awayId = awayId;
    }

    @Override
    public String toString() {
        return "\nMatchRankingDTO{" +
                "hostTeamGoals=" + hostTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", winnerId=" + winnerId +
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
                ", hostAverageAge=" + hostAverageAge +
                ", hostTeamValue=" + hostTeamValue +
                ", hostNumberOfPlayers=" + hostNumberOfPlayers +
                ", hostConfederation='" + hostConfederation + '\'' +
                ", hostPoints=" + hostPoints +
                ", hostPosition=" + hostPosition +
                ", hostRankingDate=" + hostRankingDate +
                ", awayAverageAge=" + awayAverageAge +
                ", awayTeamValue=" + awayTeamValue +
                ", awayNumberOfPlayers=" + awayNumberOfPlayers +
                ", awayConfederation='" + awayConfederation + '\'' +
                ", awayPoints=" + awayPoints +
                ", awayPosition=" + awayPosition +
                ", awayRankingDate=" + awayRankingDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", hostId=" + hostId +
                ", awayId=" + awayId +
                "}\n";
    }
}
