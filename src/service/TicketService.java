/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author dimas
 */
import dao.TicketDAO;
import dao.PassengerDAO;
import dao.VehicleDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Ticket;
import model.Passenger;
import model.Vehicle;

import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
    
     public void createTicket(String passengerId, String plate,
                             String origin, String destination) {

        Passenger passenger = passengerDAO.findPassengerById(passengerId);
        Vehicle vehicle = vehicleDAO.buscarVehiculo(plate);

        if (passenger == null) {
            System.out.println("Passenger not found");
            return;
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        
        int ticketId = nextId++;

        Ticket ticket = new Ticket(
                ticketId,         // ID asignado automáticamente
                passenger,
                vehicle,
                LocalDateTime.now(),
                origin,
                destination
        );

        ticketDAO.save(ticket);

        System.out.println("Ticket created successfully with ID: " + ticketId);
    }

    private int getNextId() {
        ArrayList<Ticket> tickets = ticketDAO.list();
        int maxId = 0;
        for (Ticket t : tickets) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        return maxId + 1;
    }
}
