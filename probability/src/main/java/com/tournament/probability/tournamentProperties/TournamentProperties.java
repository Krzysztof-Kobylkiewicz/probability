package com.tournament.probability.tournamentProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "join_table")
public class TournamentProperties {
    @Id
    @SequenceGenerator(name = "joinTableSequence",
                        sequenceName = "joinTableSequence",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "joinTableSequence")
    private int id;
}