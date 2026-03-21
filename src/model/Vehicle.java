/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public abstract class Vehicle implements Printable {

    protected String plate;
    protected Route route;
    protected int maxCapacity;
    protected int currentPassengers;
    protected double baseFare;

    public Vehicle(String plate, Route route) {
        this.plate = plate;
        this.route = route;
        this.currentPassengers = 0;
    }

    public boolean hasAvailableSeats() {
        return currentPassengers < maxCapacity;
    }

    public int availableSeats() {
        return maxCapacity - currentPassengers;
    }

    public void boardPassenger() {

        if(hasAvailableSeats()){
            currentPassengers++;
        }

    }

    public String getPlate() {
        return plate;
    }

    public Route getRoute() {
        return route;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    
    
}