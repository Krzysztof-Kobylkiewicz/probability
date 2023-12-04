package com.tournament.probability.team;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.tournament.probability.match.Match;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    //@JoinColumn(name = "host_id", referencedColumnName = "team_id")//nullable = false)
    private List<Match> matches;

    @OneToMany(mappedBy = "hostId")
    //@JoinColumn(name = "away_id", referencedColumnName = "team_id")//, nullable = false)
    private List<Match> matches1;

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

//    public void setMatches(List<Match> matches) {
//        this.matches = matches;
//    }
//
//    public void setMatches1(List<Match> matches1) {
//        this.matches1 = matches1;
//    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", email='" + email + '\'' +
//                ", matches=" + matches +
//                ", matches1=" + matches1 +
                '}';
    }
}
/* JSON
{
    "name" : "sfsffds",
    "owner" : "fsdfds",
    "email" : "fsfdfs"
}
 */
