package main;

public class Driver {
    private String name;
    private String country;
    private int points;
    private Rallycar car;

    public Driver(String name, String country, Rallycar car) {
        this.name = name;
        this.country = country;
        this.car = car;
        this.points = 0;
    }


    //Getters
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPoints() {
        return points;
    }

    public Rallycar getCar() {
        return car;
    }

    //Setters
    public void setCar(Rallycar car) {
        this.car = car;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public String toString() {
        return name + " (" + country + "): " + points + " points";
    }
}
