/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public class Buseta extends Vehicle {

    public Buseta(String plate, Route route) {

        super(plate, route);
        this.maxCapacity = 19;
        this.baseFare = 8000;

    }

    @Override
    public void printDetails() {

        System.out.println("Type: Buseta");
        System.out.println("Plate: " + plate);
        System.out.println("Route: " + route);
        System.out.println("Capacity: " + maxCapacity);
        System.out.println("Available seats: " + availableSeats());
        System.out.println("Fare: $" + baseFare);

    }

}


