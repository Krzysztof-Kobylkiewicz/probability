package com.tournament.probability.simulation;

public class TeamAndPoints {
    private String name;

    private int points;

    public TeamAndPoints(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "TeamAndPoints{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
