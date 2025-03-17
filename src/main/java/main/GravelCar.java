package main;

public class GravelCar extends Rallycar {
    private double suspensionTravel;
    
    public GravelCar(String make, String model, int horsepower, double suspensionTravel) {
        super(make, model, horsepower);
        this.suspensionTravel = suspensionTravel;
    }

    public double getSuspensionTravel() {
        return suspensionTravel;
    }

    public double calulatePerformace() {
        return Math.round((0.5*(suspensionTravel / 250.0) + 0.5* (getHorsepower()/500.0))*500);
    }

    
}