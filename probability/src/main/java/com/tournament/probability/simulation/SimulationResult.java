package com.tournament.probability.simulation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tournament.probability.team.Team;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "s_result")
class SimulationResult {
    @Id
    @SequenceGenerator(name = "simulationResultSequence",
            sequenceName = "simulationResultSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "simulationResultSequence")
    @Column(name = "s_result_id")
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "simulation_date")
    private String simulationDate;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "win_probability")
    private double winProbability;

    @Column(name = "number_of_simulations")
    private int numberOfSimulations;

    @JoinColumn( name = "team_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team teamId;

    public SimulationResult(int id,
                            String simulationDate,
                            String teamName,
                            double winProbability,
                            int numberOfSimulations,
                            Team teamId) {
        this.id = id;
        this.simulationDate = simulationDate;
        this.teamName = teamName;
        this.winProbability = winProbability;
        this.numberOfSimulations = numberOfSimulations;
        this.teamId = teamId;
    }

    public SimulationResult(String simulationDate,
                            String teamName,
                            double winProbability,
                            int numberOfSimulations,
                            Team teamId) {
        this.simulationDate = simulationDate;
        this.teamName = teamName;
        this.winProbability = winProbability;
        this.numberOfSimulations = numberOfSimulations;
        this.teamId = teamId;
    }

    public SimulationResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSimulationDate() {
        return simulationDate;
    }

    public void setSimulationDate(String simulationDate) {
        this.simulationDate = simulationDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getWinProbability() {
        return winProbability;
    }

    public void setWinProbability(double winProbability) {
        this.winProbability = winProbability;
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public void setNumberOfSimulations(int numberOfSimulations) {
        this.numberOfSimulations = numberOfSimulations;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }
}
