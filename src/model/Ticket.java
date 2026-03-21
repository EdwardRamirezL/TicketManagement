/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author dimas
 */
public class Ticket implements Printable, Calculable {
    private int id;
    private Passenger passenger;
    private Vehicle vehicle;
    private LocalDateTime dateTime;
    private String origin;
    private String destination;
    private double total;

    public Ticket(int id, Passenger passenger, Vehicle vehicle, LocalDateTime dateTime, String origin, String destination) {
        this.id = id;
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.dateTime = dateTime;
        this.origin = origin;
        this.destination = destination;
        this.total = vehicle.getBaseFare() * (1 - passenger.calculateDiscount());
    }

    public Ticket(int id, Passenger passenger, Vehicle vehicle, LocalDateTime dateTime, String origin, String destination, double total) {
        this.id = id;
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.dateTime = dateTime;
        this.origin = origin;
        this.destination = destination;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public double calculateTotal() {
        return total;
    }

    @Override
    public void printDetails() {
        System.out.println("----- TICKET -----");
        System.out.println("ID: " + id);
        System.out.println("Passenger: " + passenger.getName() + " (" + passenger.getId() + ")");
        System.out.println("Vehicle: " + vehicle.getPlate() + " - " + vehicle.getClass().getSimpleName());
        System.out.println("Route: " + vehicle.getRoute());
        System.out.println("Date/Time: " + dateTime);
        System.out.println("Origin: " + origin + " | Destination: " + destination);
        System.out.printf("Total: $%.2f%n", calculateTotal());
        System.out.println("-------------------");
    }
}
