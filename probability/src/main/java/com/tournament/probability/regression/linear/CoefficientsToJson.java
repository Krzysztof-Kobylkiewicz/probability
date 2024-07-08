package com.tournament.probability.regression.linear;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
class CoefficientsToJson {

    ObjectMapper objectMapper = new ObjectMapper();

    private final double scale = Math.pow(10, 4);

    String coefficientsToJson(double [] olsCoefficients, ArrayList<String> labels){

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode coefficientVariablePair = objectMapper.createObjectNode();

        String jsonString = null;
        coefficientVariablePair.put("Intercept", olsCoefficients[0]);
        for (int i = 1; i < olsCoefficients.length; i++){
            coefficientVariablePair.put(labels.get(i-1), olsCoefficients[i]);
        }
        try{
            jsonString = objectMapper.writeValueAsString(coefficientVariablePair);
        } catch (Exception e){
            throw new RuntimeException("Failed to convert coefficients and variables to JSON.");
        }
        System.out.println("\nLinear regression coefficients:\n" + jsonString);

        return jsonString;
    }

}
