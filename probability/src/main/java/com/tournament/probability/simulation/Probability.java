package com.tournament.probability.simulation;

class Probability {

    private double win;

    private double draw;

    private double lose;

    private String host;

    private String visitor;

    public Probability() {
    }

    public Probability(String host, String visitor) {
        this.host = host;
        this.visitor = visitor;
    }

    public Probability(double win, double draw, double lose, String host, String visitor) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.host = host;
        this.visitor = visitor;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public double getDraw() {
        return draw;
    }

    public void setDraw(double draw) {
        this.draw = draw;
    }

    public double getLose() {
        return lose;
    }

    public void setLose(double lose) {
        this.lose = lose;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    @Override
    public String toString() {
        return "\nProbability{" +
                "host='" + host + '\'' +
                "win=" + win +
                ", draw=" + draw +
                ", lose=" + lose +
                ", visitor='" + visitor + '\'' +
                "}\n";
    }
}
