package main;


import java.util.ArrayList;
import java.util.List;

public class ChampionshipManager {
    // Singleton
    private static ChampionshipManager instance;
    private ChampionshipManager() {
        this.drivers = new ArrayList<Driver>();
        this.rallyRaceResults = new ArrayList<RallyRaceResults>();
        this.gravelCars = new ArrayList<Rallycar>();
        this.asphaltCars = new ArrayList<Rallycar>();
        totalDrivers = 0;
        totalRaces = 0;
    }
    public static ChampionshipManager getInstance(){
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    // Attributes
    private List<Driver> drivers;
    private List<RallyRaceResults> rallyRaceResults;

    private List<Rallycar> gravelCars;
    private List<Rallycar> asphaltCars;

    private static int totalDrivers;
    private static int totalRaces;

    // Methods
    //Add a driver to the list of drivers
    public void registerDriver(Driver driver) {
        this.drivers.add(driver);
        totalDrivers++;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    //Record a race result
    public void registerRaceResults(RallyRaceResults rallyRaceResults) {
        this.rallyRaceResults.add(rallyRaceResults);
        totalRaces++;
    }

    public List<Driver> getDriverStandings() {
        List<Driver> driver_standing = drivers;
        driver_standing.sort((driver1, driver2) -> driver2.getPoints() - driver1.getPoints());
        return driver_standing;

    }

    public static Driver getLeadingDriver(){
        Driver leadingDriver = null;
        int maxPoints = 0;
        for (Driver driver : instance.drivers){
            if (driver.getPoints() > maxPoints){
                maxPoints = driver.getPoints();
                leadingDriver = driver;
            }
        }
        return leadingDriver;   
    }

    public static int getTotalChampionshipPoints(){
        return instance.drivers.stream().mapToInt(Driver::getPoints).sum();
    }

    public void registerCar(Rallycar car){
        if (car instanceof GravelCar){
            gravelCars.add(car);
        } else {
            asphaltCars.add(car);
        }
    }

    public List<Rallycar> getGravelCars() {
        return gravelCars;
    }

    public List<Rallycar> getAsphaltCars() {
        return asphaltCars;
    }



    public static int getTotalDrivers() {
        return totalDrivers;
    }

    public static int getTotalRaces() {
        return totalRaces;
    }

    public List<RallyRaceResults> getRallyRaceResults() {
        return rallyRaceResults;
    }






}
