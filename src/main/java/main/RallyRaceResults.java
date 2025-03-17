package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RallyRaceResults implements RaceResult {
    private String raceName;
    private String location;
    Map<Driver, Integer> results;


    public RallyRaceResults(String raceName, String location) {
        this.raceName = raceName;
        this.location = location;
        this.results = new HashMap<Driver, Integer>();
    }

    public String getRaceName() {
        return raceName;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void recordResult(Driver driver, int posistion, int points) {
        results.put(driver, posistion);
        driver.addPoints(points);
    }

    @Override
    public int getDriverPoints(Driver driver) {
        return driver.getPoints();
    }
    @Override
    public List<Driver> getResults() {
        List<Driver> standing = results.keySet().stream().collect(Collectors.toCollection(ArrayList::new)); 
        standing.sort((driver1, driver2) -> results.get(driver1) - (results.get(driver2)));
        return standing;
    }

}
