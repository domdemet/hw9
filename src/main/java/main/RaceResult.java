package main;

import java.util.List;

public interface RaceResult {
    public void recordResult(Driver driver, int posistion, int points);
    public int getDriverPoints(Driver driver);
    public List<Driver> getResults();
}
