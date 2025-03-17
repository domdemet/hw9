package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RallyChampionship {
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        // Lists to store gravel cars and asphalt cars
        

        // Create and reister gravel cars
        manager.registerCar(new GravelCar("Toyota", "Yaris", 380, 260));
        manager.registerCar(new GravelCar("Ford", "Fiesta", 370, 255));
        manager.registerCar(new GravelCar("Hyundai", "i20", 385, 265));
        manager.registerCar(new GravelCar("Skoda", "Fabia", 375, 250));

        // Create and register asphalt cars
        manager.registerCar(new AsphaltCar("Hyundai", "i20", 420, 280));
        manager.registerCar(new AsphaltCar("Citroën", "C3", 410, 270));
        manager.registerCar(new AsphaltCar("Toyota", "GR Yaris", 430, 290));
        manager.registerCar(new AsphaltCar("Ford", "Puma", 415, 260));

        // Register drivers with their gravel cars
        List<Rallycar> gravelCars = manager.getGravelCars();
        manager.registerDriver(new Driver("Sébastien Ogier", "France", gravelCars.get(0)));
        manager.registerDriver(new Driver("Kalle Rovanperä", "Finland", gravelCars.get(1)));
        manager.registerDriver(new Driver("Ott Tänak", "Estonia", gravelCars.get(2)));
        manager.registerDriver(new Driver("Thierry Neuville", "Belgium", gravelCars.get(3)));

        // Race, positions are decided randomly
        race(manager,"GravelRally", "Somewhere", "gravel");
        //manager.getDriverStandings().forEach((driver) -> System.out.println(driver.getName() + driver.getPoints()));       
        
        race(manager, "AsphaltRally", "Middle of nowhere", "asphalt");
        //manager.getDriverStandings().forEach((driver) -> System.out.println(driver.getName() + driver.getPoints()));

        //Print the required output
        //Standings
        List<Driver> standings = manager.getDriverStandings();
        for (int i = 0; i < standings.size(); i++) {
            Driver driver = standings.get(i);
            System.out.println(i+1 + ". " + driver);
        }
        //Leading driver
        System.out.println("\n===== CHAMPIONSHIP LEADER =====\n"+ ChampionshipManager.getLeadingDriver());
        //stats
        System.out.println("\n===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total drivers: " + ChampionshipManager.getTotalDrivers());
        System.out.println("Total races: " + ChampionshipManager.getTotalRaces());
        System.out.println("Average points per driver: " + ChampionshipStatistics.calculateAveragePointsPerDriver(standings));
        System.out.println("Most successful country: " + ChampionshipStatistics.findMostSuccesfulCountry(standings));
        System.out.println("Total Championship Points: " + ChampionshipManager.getTotalChampionshipPoints());

        //Results
        System.out.println("\n===== RACE RESULTS =====");
        for (RallyRaceResults raceResult : manager.getRallyRaceResults()) {
            System.out.println("Race: " + raceResult.getRaceName() + " (" + raceResult.getLocation()+")");
            List<Driver> currentResults = raceResult.getResults();
            for (int i = 1; i <= currentResults.size(); i++) {
                Driver driver = currentResults.get(i-1);
                System.out.println("Position "+ i + ": " + driver.getName() + " - " + (25 - (i - 1) * 3 > 0 ? 25 - (i - 1) * 3 : 0));
                }
            System.out.println();
            }

        System.out.println("\n===== CAR PERFORMANCE RATINGS =====");
        //Car performaces
        System.out.println("Gravel cars:");
        for (Rallycar car : manager.getGravelCars()) {
            System.out.println(car.getMake() + " " + car.getModel() + " Performance: " + car.calulatePerformace());
        }

        System.out.println("\nAsphalt cars:");
        for (Rallycar car : manager.getAsphaltCars()) {
            System.out.println(car.getMake() + " " + car.getModel() + " Performance: " + car.calulatePerformace());
        }

                

    }

    private static void race(ChampionshipManager manager,String name, String location, String surface) { 
        //switch cars if needed
        if(!manager.getDrivers().get(0).getCar().getClass().getSimpleName().toLowerCase().startsWith(surface)){
            switchCars(manager);
        }
        RallyRaceResults rallyRaceResults = new RallyRaceResults(name, location);
        List<Driver> selectDrivers = manager.getDrivers().stream().collect(Collectors.toCollection(ArrayList::new));


        Random rand = new Random();
        int participants = selectDrivers.size();
        for (int i = 1; i <= participants; i++) {
            int randomDriverIndex = rand.nextInt(selectDrivers.size());
            Driver currentDriver = selectDrivers.remove(randomDriverIndex);
            rallyRaceResults.recordResult(currentDriver, i, 25 - (i - 1) * 3 > 0 ? 25 - (i - 1) * 3 : 0);
        }
        manager.registerRaceResults(rallyRaceResults);


    }

    private static void switchCars(ChampionshipManager manager) {
        List<Driver> drivers = manager.getDrivers();
        if (drivers.get(0).getCar() instanceof GravelCar) {
            Iterator<Rallycar> asphaltCarIterator = manager.getAsphaltCars().iterator();
            for (Driver driver : drivers) {
                driver.setCar(asphaltCarIterator.next());
            }
        } else {
            Iterator<Rallycar> gravelCarIterator = manager.getGravelCars().iterator();
            for (Driver driver : drivers) {
                driver.setCar(gravelCarIterator.next());
            }
        }

    }

}
