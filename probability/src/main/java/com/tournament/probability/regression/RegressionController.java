package com.tournament.probability.regression;

import com.tournament.probability.regression.logistic.DataReturnedFromLogisticDTO;
import com.tournament.probability.regression.logistic.LogisticRegressionModel;
import com.tournament.probability.simulation.SimulateTournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/regression")
public class RegressionController {

    @Autowired
    private final SimulateTournament simulateTournament;

    @Autowired
    private final LogisticRegressionModel logisticRegressionModel;

    public RegressionController(SimulateTournament simulateTournament,
                                LogisticRegressionModel logisticRegressionModel) {
        this.simulateTournament = simulateTournament;
        this.logisticRegressionModel = logisticRegressionModel;
    }

    @GetMapping("/predict/euro2024/{numSimulations}")
    public ResponseEntity<Map<String, Double>> predictEuro2024(@PathVariable int numSimulations){

        DataReturnedFromLogisticDTO fromLogisticDTO = logisticRegressionModel.logisticRegression();

        Map<String, Double> probabilities = simulateTournament.createEuro2024Groups(fromLogisticDTO.eval(),
                                                              fromLogisticDTO.evalTest(),
                                                              fromLogisticDTO.model(),
                                                              fromLogisticDTO.attributes(),
                                                              fromLogisticDTO.testData(),
                                                              numSimulations);

        return new ResponseEntity<>(probabilities, HttpStatus.OK);
    }
}
