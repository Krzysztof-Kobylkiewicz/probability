//package com.tournament.probability.team;
//
//import com.tournament.probability.match.Match;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import java.util.List;
//import java.util.ArrayList;
//
//@Component
//public class TeamDataLoader implements ApplicationRunner {
//    private final TeamRepository teamRepository;
//    @Autowired
//    public TeamDataLoader(TeamRepository teamRepository) {
//        this.teamRepository = teamRepository;
//    }
//    public void run(ApplicationArguments args) {
//        ArrayList<Team> TeamList = new ArrayList<Team>();
//        TeamList.add(new Team(1, "Kshpichki CF", "Kshpichek", "krzysiek12-2000@o2.pl");
//        TeamList.add(new Team(2, "Porto Wtorek", "Fnexar", "email@o2.pl"));
//        teamRepository.saveAll(TeamList);
//    }
//}
