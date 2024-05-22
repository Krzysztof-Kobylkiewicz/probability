package com.tournament.probability.tournamentType;

import com.tournament.probability.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TournamentTypeService {
    @Autowired
    private final TournamentTypeRepository tournamentTypeRepository;

    TournamentTypeService(TournamentTypeRepository tournamentTypeRepository){
        this.tournamentTypeRepository = tournamentTypeRepository;
    }
    Optional<TournamentType> getTournamentTypeByIdMethod(int id) {
        Optional<TournamentType> TournamentTypeOptional =
                tournamentTypeRepository.findTournamentTypeById(id);
        if (TournamentTypeOptional.isPresent()){
            return tournamentTypeRepository.findTournamentTypeById(id);
        } throw new ApiRequestException("A tournament type of id: " + id + " does not exist.");

    }
    TournamentType addTournamentType(TournamentType tournamentType){
        Optional<TournamentType> optionalTournamentType = tournamentTypeRepository.findByType(tournamentType.getTournamentType());
        if (optionalTournamentType.isPresent()){
            throw new IllegalStateException("Tournament Type '" + tournamentType.getTournamentType()+ "' already exists.");
        }
        return tournamentTypeRepository.save(tournamentType);
    }
    TournamentType updateTournamentType(TournamentType tournamentType, int id){
        TournamentType tournamentTypeToUpdate = tournamentTypeRepository.findTournamentTypeById(id).
                orElseThrow(() -> new ApiRequestException("Tournament type with id '" + id + "' does not exist."));

        tournamentTypeToUpdate.setTournamentType(tournamentType.getTournamentType());
        tournamentTypeToUpdate.setTournamentTypeDescription(tournamentType.getTournamentTypeDescription());
        tournamentTypeToUpdate.setMaxTeams(tournamentType.getMaxTeams());
        tournamentTypeToUpdate.setMinTeams(tournamentType.getMinTeams());

        return tournamentTypeRepository.save(tournamentTypeToUpdate);
    }
    void deleteTournamentType(int id){
        tournamentTypeRepository.deleteTournamentTypeById(id);
    }
}
