package com.tournament.probability.regression.linear;

import com.tournament.probability.regression.MatchRankingDTO;
import com.tournament.probability.regression.PreparedXDTO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PrepareData implements PrepareDataInterface {

    @Override
    public PreparedXDTO prepareXDataToModel(List<MatchRankingDTO> data){

        int rows = data.size();
        int columns = 20;

        double[][] X = new double[rows][columns];

        Method[] methods = MatchRankingDTO.class.getMethods();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < rows; i++) {

            MatchRankingDTO dataRow = data.get(i);
            int attrIndex = 0;

            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    try {
                        Object value = method.invoke(dataRow);

                        if (method.getName().equals("getName") ||
                                method.getName().equals("getHostId") ||
                                method.getName().equals("getAwayId") ||
                                method.getName().equals("getId") ||
                                method.getName().equals("getAwayRankingDate") ||
                                method.getName().equals("getHostRankingDate") ||
                                method.getName().equals("getAwayNumberOfPlayers") ||
                                method.getName().equals("getAwayTeamValue") ||
                                method.getName().equals("getAwayAverageAge") ||
                                method.getName().equals("getHostNumberOfPlayers") ||
                                method.getName().equals("getHostTeamValue") ||
                                method.getName().equals("getHostAverageAge") ||
                                method.getName().equals("getClass") ||
                                method.getName().equals("getWinnerId") ||
                                method.getName().equals("getHostTeamGoals") ||
                                method.getName().equals("getAwayTeamGoals")
                        ){

                            /*skipping those methods*/

                        } else if (value instanceof Number /*&& !method.getName().equals("getWinnerId")*/) {

                            X[i][attrIndex] = ((Number) value).doubleValue();
                            attrIndex += 1;
                            if(!labels.contains(method.getName().replaceFirst("get", ""))){
                                labels.add(method.getName().replaceFirst("get", ""));
                            }


                        } else if (value instanceof String && method.getName().equals("getHostConfederation") ||
                                                              method.getName().equals("getAwayConfederation")) {

                            if(!labels.contains(method.getName().replaceFirst("get", ""))){
                                labels.add(method.getName().replaceFirst("get", ""));
                            }
                            switch (((String) value)) {
                                case "UEFA":
                                    X[i][attrIndex] = 0;
                                    attrIndex += 1;
                                    break;
                                case "CONMEBOL":
                                    X[i][attrIndex] = 1;
                                    attrIndex += 1;
                                    break;
                                case "CONCACAF":
                                    X[i][attrIndex] = 2;
                                    attrIndex += 1;
                                    break;
                                case "CAF":
                                    X[i][attrIndex] = 3;
                                    attrIndex += 1;
                                    break;
                                case "AFC":
                                    X[i][attrIndex] = 4;
                                    attrIndex += 1;
                                    break;
                                case "OFC":
                                    X[i][attrIndex] = 5;
                                    attrIndex += 1;
                                    break;
                            }
                        } else {

                            throw new IllegalAccessException("Method: " + method.getName() + " returns not supported variable type.");
                        }
                    } catch (Exception e) {

                        throw new RuntimeException("Error accessing attribute value " + method.getName(), e);
                    }
                }

            }
        }
        return new PreparedXDTO(X, labels);
    }

    @Override
    public double[] prepareYDataToModel(List<MatchRankingDTO> data){

        int rows = data.size();

        double[] Y = new double[rows];

        Method[] methods = MatchRankingDTO.class.getMethods();

        for (int i = 0; i < rows; i++) {

            MatchRankingDTO dataRow = data.get(i);
            int attrIndex = 0;

            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    try {
                        Object value = method.invoke(dataRow);

                        if (method.getName().equals("getWinnerId")){
                            if (value == null){

                                Y[i] = 0.0;

                            } else {

                                Y[i] = (dataRow.getWinnerId() == dataRow.getHostId() ? 1.0 : 0.0);
                            }
                        }
                    } catch (Exception e) {

                        throw new RuntimeException("Error accessing attribute value " + method.getName(), e);
                    }
                }

            }
        }
        return Y;
    }
}
