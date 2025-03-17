package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStatistics {
    public static double calculateAveragePointsPerDriver(List<Driver> drivers) {
        double totalPoints = 0;
        for (Driver driver : drivers) {
            totalPoints += driver.getPoints();
        }
        return totalPoints / drivers.size();
    }

    public static String findMostSuccesfulCountry(List<Driver> drivers) {
        Map<String, Integer> countryPoints = new HashMap<String, Integer>();
        for (Driver driver : drivers) {
            String country = driver.getCountry();
            if (countryPoints.containsKey(country)) {
                countryPoints.put(country, countryPoints.get(country) + driver.getPoints());
            } else {
                countryPoints.put(country, driver.getPoints());
            }
        }

        String bestCountry = countryPoints.keySet().iterator().next();
        for (String key : countryPoints.keySet()) {
            if (countryPoints.get(key) > countryPoints.get(bestCountry)) {
                bestCountry = key;
            }
        }
        return bestCountry;
    }

    public static int getTotalRacesHeld() {
        return ChampionshipManager.getTotalRaces();
    }

}
