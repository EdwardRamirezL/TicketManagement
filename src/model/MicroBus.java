/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */


public class MicroBus extends Vehicle {

    public MicroBus(String plate, String route) {

        super(plate, route);
        this.maxCapacity = 25;
        this.baseFare = 10000;

    }

    @Override
    public void printDetails() {

        System.out.println("Type: MicroBus");
        System.out.println("Plate: " + plate);
        System.out.println("Route: " + route);
        System.out.println("Capacity: " + maxCapacity);
        System.out.println("Available seats: " + availableSeats());
        System.out.println("Fare: $" + baseFare);

    }

}
