package com.tournament.probability.tournament;

import com.tournament.probability.match.Match;
import com.tournament.probability.tournamentProperties.TournamentProperties;
import com.tournament.probability.tournamentType.TournamentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournament")
@NoArgsConstructor
@AllArgsConstructor
//@Component
public class Tournament {
    @Id
    @SequenceGenerator(name = "tournamentIdSequence",
            sequenceName = "tournamentIdSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tournamentIdSequence")
    @Column(name = "tournament_id")
    private int id;
    @Column(name = "tournament_name")
    private String name;
    @Column(name = "teams_participating")
    //@Min(value = tournamentType.getMinTeams())
    private int teamsParticipating;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "winner")
    private String winner;

    @OneToMany(targetEntity = TournamentProperties.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "tournament_id", nullable = false)
    private List<TournamentProperties> tournamentPropertiesList1;

//    @OneToMany(targetEntity = Match.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "tournament_id", referencedColumnName = "tournament_id", nullable = false)
//    List<Match> matches;

    //Constructors
    public Tournament(String name, int teamsParticipating, LocalDate startDate,
                      LocalDate endDate, String winner) {
        this.name = name;
        this.teamsParticipating = teamsParticipating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winner = winner;
    }
//    @Autowired
//    Tournament(TournamentType tournamentType){
//        this.tournamentType = tournamentType;
//    }
//    @Transient
//    private TournamentType tournamentType;
}