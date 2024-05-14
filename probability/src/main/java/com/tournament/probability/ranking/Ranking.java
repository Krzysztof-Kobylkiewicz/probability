package com.tournament.probability.ranking;

import com.tournament.probability.team.Team;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity(name = "ranking")
@Table
public class Ranking {
    @Id
    @SequenceGenerator(name = "rankingSequence", sequenceName = "rankingSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rankingSequence")
    private long id;
    @JoinColumn( name = "teamId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team teamId;

    private float averageAge;

    @Nullable
    private float teamValue;

    private int numberOfPlayers;

    private String confederation;

    private int points;

    private int position;

    public Ranking (float averageAge, float teamValue, int numberOfPlayers, String confederation, int points, int position){
        this.averageAge = averageAge;
        this.teamValue = teamValue;
        this.numberOfPlayers = numberOfPlayers;
        this.confederation = confederation;
        this.points = points;
        this.position = position;
    }

    long getId(){
        return id;}

    Team getTeamId(){
        return teamId;}

    float getAverageAge(){
        return averageAge;}

    float getTeamValue(){
        return teamValue;}

    String getConfederation(){
        return confederation;}

    int getPoints(){
        return points;}

    int getPosition(){
        return position;}

    public void setId(long id) {
        this.id = id;}

    public void setTeamId(Team teamId) {
        this.teamId = teamId;}

    void setAverageAge(float averageAge){
        this.averageAge = averageAge;}

    public void setTeamValue(float teamValue) {
        this.teamValue = teamValue;}

    public void setConfederation(String confederation) {
        this.confederation = confederation;}

    public void setPoints(int points) {
        this.points = points;}

    public void setPosition(int position) {
        this.position = position;}

    public int getNumberOfPlayers() {
        return numberOfPlayers;}

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;}

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
                '}';
    }
}