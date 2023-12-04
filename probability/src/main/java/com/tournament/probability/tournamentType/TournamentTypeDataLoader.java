//package com.tournament.probability.tournamentType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
///*
//This class is used to insert default types of tournament to the database while starting the application
// */
//@Component
//class TournamentTypeDataLoader implements ApplicationRunner {
//    private final TournamentTypeRepository tournamentTypeRepository;
//    @Autowired
//    public TournamentTypeDataLoader(TournamentTypeRepository tournamentTypeRepository) {
//        this.tournamentTypeRepository = tournamentTypeRepository;
//    }
//    public void run(ApplicationArguments args) {
//        ArrayList<TournamentType> TournamentTypeList = new ArrayList<TournamentType>();
//        TournamentTypeList.add(new TournamentType(1, 'L', "League", 2, 24));
//        TournamentTypeList.add(new TournamentType(2, 'W', "World Cup", 32, 32));
//        TournamentTypeList.add(new TournamentType(3, 'E', "European Championship", 24, 24));
//        TournamentTypeList.add(new TournamentType(4, 'F', "Felicita", 3, 3));
//        tournamentTypeRepository.saveAll(TournamentTypeList);
//    }
//}