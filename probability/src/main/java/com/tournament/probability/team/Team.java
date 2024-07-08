package com.tournament.probability.team;

import com.tournament.probability.ranking.Ranking;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import com.tournament.probability.match.Match;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @SequenceGenerator(name = "teamSequence",
            sequenceName = "teamSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "teamSequence")
    @Column(name = "team_id")
    private int id;
    @Column(name = "team_name")
    @Size(max = 30)
    private String name;
    @Column(name = "team_owner")
    @Size(max = 30)
    private String owner;
    @Column(name = "owner_email")
    @Size(max = 40)
    private String email;

    @OneToMany(mappedBy = "hostId")
    private List<Match> matches;

    @OneToMany(mappedBy = "awayId")
    private List<Match> matches1;

    @OneToMany(mappedBy = "teamId")
    private List<Ranking> rankings;

    Team(String name, String owner, String email, List<Match> matches, List<Match> matches1) {
        this.name = name;
        this.owner = owner;
        this.email = email;
    }

    Team(int id, String name, String owner, String email) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.email = email;
    }

    Team(String name, String owner, String email, List<Match> matches) {
        this.name = name;
        this.owner = owner;
        this.email = email;
//        this.matches = matches;
    }
    Team(String name, String owner, String email, List<Match> matches, List<Match> matches1, List<Ranking> rankings) {
        this.name = name;
        this.owner = owner;
    }
    Team(String name, String owner, List<Ranking> rankings) {
        this.name = name;
    }
    public Team(){
    }

    public Team(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ranking> getRankings() {
        return rankings;}

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;}

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}