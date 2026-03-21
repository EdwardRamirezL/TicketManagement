/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */


public class Bus extends Vehicle {

    public Bus(String plate, Route route) {

        super(plate, route);
        this.maxCapacity = 45;
        this.baseFare = 15000;

    }

    @Override
    public void printDetails() {

        System.out.println("Type: Bus");
        System.out.println("Plate: " + plate);
        System.out.println("Route: " + route);
        System.out.println("Capacity: " + maxCapacity);
        System.out.println("Available seats: " + availableSeats());
        System.out.println("Fare: $" + baseFare);

    }

}
