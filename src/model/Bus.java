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

        System.out.println("Tipo: Bus");
        System.out.println("Placa: " + plate);
        System.out.println("Route: " + route);
        System.out.println("Capacidad: " + maxCapacity);
        System.out.println("Puestos Disponibles: " + availableSeats());
        System.out.println("Tarifa: $" + baseFare);
    }
    

}
