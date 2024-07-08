package com.tournament.probability.regression.logistic;

public class MatchAvgDataDTO {
    private Integer hostConfederation;
    private Integer awayConfederation;
    private Double hostAttemptsOnTarget;
    private Double awayAttemptsOnTarget;
    private Double hostAttemptsOffTarget;
    private Double awayAttemptsOffTarget;
    private Double hostParaden;
    private Double awayParaden;
    private Double hostCorners;
    private Double awayCorners;
    private Double hostFreeKicks;
    private Double awayFreeKicks;
    private Double hostFouls;
    private Double awayFouls;
    private Double hostOffsides;
    private Double awayOffsides;
    private Integer hostPoints;
    private Integer hostPosition;
    private Integer awayPoints;
    private Integer awayPosition;


    public MatchAvgDataDTO(Double hostAttemptsOnTarget, Double awayAttemptsOnTarget, Double hostAttemptsOffTarget, Double awayAttemptsOffTarget, Double hostParaden, Double awayParaden, Double hostCorners, Double awayCorners, Double hostFreeKicks, Double awayFreeKicks, Double hostFouls, Double awayFouls, Double hostOffsides, Double awayOffsides) {
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
    }

    public Double getHostAttemptsOnTarget() {
        return hostAttemptsOnTarget;
    }

    public void setHostAttemptsOnTarget(Double hostAttemptsOnTarget) {
        this.hostAttemptsOnTarget = hostAttemptsOnTarget;
    }

    public Double getAwayAttemptsOnTarget() {
        return awayAttemptsOnTarget;
    }

    public void setAwayAttemptsOnTarget(Double awayAttemptsOnTarget) {
        this.awayAttemptsOnTarget = awayAttemptsOnTarget;
    }

    public Double getHostAttemptsOffTarget() {
        return hostAttemptsOffTarget;
    }

    public void setHostAttemptsOffTarget(Double hostAttemptsOffTarget) {
        this.hostAttemptsOffTarget = hostAttemptsOffTarget;
    }

    public Double getAwayAttemptsOffTarget() {
        return awayAttemptsOffTarget;
    }

    public void setAwayAttemptsOffTarget(Double awayAttemptsOffTarget) {
        this.awayAttemptsOffTarget = awayAttemptsOffTarget;
    }

    public Double getHostParaden() {
        return hostParaden;
    }

    public void setHostParaden(Double hostParaden) {
        this.hostParaden = hostParaden;
    }

    public Double getAwayParaden() {
        return awayParaden;
    }

    public void setAwayParaden(Double awayParaden) {
        this.awayParaden = awayParaden;
    }

    public Double getHostCorners() {
        return hostCorners;
    }

    public void setHostCorners(Double hostCorners) {
        this.hostCorners = hostCorners;
    }

    public Double getAwayCorners() {
        return awayCorners;
    }

    public void setAwayCorners(Double awayCorners) {
        this.awayCorners = awayCorners;
    }

    public Double getHostFreeKicks() {
        return hostFreeKicks;
    }

    public void setHostFreeKicks(Double hostFreeKicks) {
        this.hostFreeKicks = hostFreeKicks;
    }

    public Double getAwayFreeKicks() {
        return awayFreeKicks;
    }

    public void setAwayFreeKicks(Double awayFreeKicks) {
        this.awayFreeKicks = awayFreeKicks;
    }

    public Double getHostFouls() {
        return hostFouls;
    }

    public void setHostFouls(Double hostFouls) {
        this.hostFouls = hostFouls;
    }

    public Double getAwayFouls() {
        return awayFouls;
    }

    public void setAwayFouls(Double awayFouls) {
        this.awayFouls = awayFouls;
    }

    public Double getHostOffsides() {
        return hostOffsides;
    }

    public void setHostOffsides(Double hostOffsides) {
        this.hostOffsides = hostOffsides;
    }

    public Double getAwayOffsides() {
        return awayOffsides;
    }

    public void setAwayOffsides(Double awayOffsides) {
        this.awayOffsides = awayOffsides;
    }

    public Integer getHostConfederation() {
        return hostConfederation;
    }

    public void setHostConfederation(Integer hostConfederation) {
        this.hostConfederation = hostConfederation;
    }

    public Integer getAwayConfederation() {
        return awayConfederation;
    }

    public void setAwayConfederation(Integer awayConfederation) {
        this.awayConfederation = awayConfederation;
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

    @Override
    public String toString() {
        return "\nMatchAvgDataDTO{" +
                "hostConfederation=" + hostConfederation +
                ", awayConfederation=" + awayConfederation +
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
                ", hostPoints=" + hostPoints +
                ", hostPosition=" + hostPosition +
                ", awayPoints=" + awayPoints +
                ", awayPosition=" + awayPosition +
                "}\n";
    }
}
