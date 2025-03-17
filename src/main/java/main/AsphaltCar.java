package main;

public class AsphaltCar extends Rallycar{
    private double downforce;
    
    public AsphaltCar(String make, String model, int horsepower, double downforce) {
        super(make, model, horsepower);
        this.downforce = downforce;
    }

    public double getDownforce() {
        return downforce;
    }

    public double calulatePerformace() {
        return Math.round((0.5*(downforce / 275.0) + 0.5* (getHorsepower()/500))*500);
    }
    
}
