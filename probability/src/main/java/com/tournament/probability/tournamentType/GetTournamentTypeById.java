package com.tournament.probability.tournamentType;

import com.tournament.probability.exception.ApiRequestException;
import com.tournament.probability.exception.TournamentTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetTournamentTypeById {
    private final TournamentTypeRepository tournamentTypeRepository;
    @Autowired
    GetTournamentTypeById(TournamentTypeRepository tournamentTypeRepository){
        this.tournamentTypeRepository = tournamentTypeRepository;
    }
    Optional<TournamentType> getTournamentTypeByIdMethod(int id) {
        Optional<TournamentType> TournamentTypeOptional =
                tournamentTypeRepository.findTournamentTypeById(id);
        if (TournamentTypeOptional.isPresent()){
            return tournamentTypeRepository.findTournamentTypeById(id);
        } throw new ApiRequestException("A tournament type of id: " + id + " does not exist.");

    }
}
