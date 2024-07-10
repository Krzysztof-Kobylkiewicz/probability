package com.tournament.probability.tournamentType;

import com.tournament.probability.tournament.Tournament;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "tournament_type")
public class TournamentType {
    @Id
    @SequenceGenerator(name = "tournamentTypeSequence",
            sequenceName = "tournamentTypeSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tournamentTypeSequence")
    @Column(name = "tournament_type_id")
    private int id;

    @Column(name = "type")
    private Character tournamentType;

    @Column(name = "description")
    private String tournamentTypeDescription;

    @Column(name = "min_teams")
    private int minTeams;

    @Column(name = "max_teams")
    private int maxTeams;

    @OneToMany(mappedBy = "tournamentTypeId")
    private List<Tournament> tournaments;

    public TournamentType() {
    }

    public TournamentType(char type, String description, int minTeams, int maxTeams) {
        this.tournamentType = type;
        this.tournamentTypeDescription = description;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    @Override
    public String toString() {
        return "TournamentType{" +
                "id=" + id +
                ", tournamentType='" + tournamentType + '\'' +
                ", tournamentTypeDescription='" + tournamentTypeDescription + '\'' +
                ", minTeams=" + minTeams +
                ", maxTeams=" + maxTeams +
                '}';
    }

    public void setTournamentType(char tournamentType) {
        this.tournamentType = tournamentType;
    }

    public void setTournamentTypeDescription(String tournamentTypeDescription) {
        this.tournamentTypeDescription = tournamentTypeDescription;
    }

    public void setMinTeams(int minTeams) {
        this.minTeams = minTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public int getId() {
        return id;
    }

    public char getTournamentType() {
        return tournamentType;
    }

    public String getTournamentTypeDescription() {
        return tournamentTypeDescription;
    }

    public int getMinTeams() {
        return minTeams;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setId(int id) {
        this.id = id;
    }
}