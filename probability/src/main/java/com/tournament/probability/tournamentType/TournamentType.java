package com.tournament.probability.tournamentType;

import com.tournament.probability.tournament.Tournament;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "tournament_type")
@Component
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
    private char tournamentType;
    @Column(name = "description")
    private String tournamentTypeDescription;
    @Column(name = "min_teams")
    private int minTeams;
    @Column(name = "max_teams")
    private int maxTeams;

    @OneToMany(targetEntity = Tournament.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "type", referencedColumnName = "type", nullable = false)
    private List<Tournament> tournaments;
    //Constructors

    public TournamentType(int id, char tournamentType, String tournamentTypeDescription, int minTeams, int maxTeams) {
        this.id = id;
        this.tournamentType = tournamentType;
        this.tournamentTypeDescription = tournamentTypeDescription;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    public TournamentType(char type, String description, int minTeams, int maxTeams) {
        this.tournamentType = type;
        this.tournamentTypeDescription = description;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    //toString()

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

    //Setters

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

    //Getters

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
}
/* JSON
{
        "tournamentType" : "L",
        "tournamentTypeDescription" : "dfdfsgdg",
        "minTeams" : 16,
        "maxTeams" : 24
}
*/