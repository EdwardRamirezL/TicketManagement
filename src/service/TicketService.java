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

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
    
     public void createTicket(String passengerId, String plate,
                             String origin, String destination) {

        Passenger passenger = passengerDAO.findPassengerById(passengerId);
        Vehicle vehicle = vehicleDAO.getVehicleByPlate(plate);

        if (passenger == null) {
            System.out.println("Passenger not found");
            return;
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        long occupiedSeats = ticketDAO.list().stream()
                .filter(t -> t.getVehicle().getPlate().equalsIgnoreCase(plate))
                .count();
        if (occupiedSeats >= vehicle.getMaxCapacity()) {
            System.out.println("Vehicle has no available seats (capacity: " + vehicle.getMaxCapacity() + ")");
            return;
        }

        int ticketId = getNextId();

        Ticket ticket = new Ticket(
                ticketId,         
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
    
    public ArrayList<Ticket> getAllTickets() {
        return ticketDAO.list();
    }
    
    public void showTickets() {
        ArrayList<Ticket> tickets = getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found");
            return;
        }

        for (Ticket t : tickets) {
            t.printDetails();
        }
    }
    
    public Ticket findTicketById(int ticketId) {
        for (Ticket t : ticketDAO.list()) {
            if (t.getId() == ticketId) {
                return t;
            }
        }
        return null;
    }
    
    public boolean deleteTicket(int ticketId) {
        ArrayList<Ticket> tickets = ticketDAO.list();
        ArrayList<Ticket> updatedTickets = new ArrayList<>();
        boolean found = false;

        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {
                found = true;
            } else {
                updatedTickets.add(t);
            }
        }

        if (found) {
            ticketDAO.rewriteAll(updatedTickets);
            System.out.println("Ticket deleted successfully");
            return true;
        } else {
            System.out.println("Ticket not found");
            return false;
        }
    }
    
    public boolean updateTicket(int ticketId, String newDestination) {
        ArrayList<Ticket> tickets = ticketDAO.list();
        boolean found = false;

        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {
                found = true;
                break;
            }
        }

        if (found) {
            ArrayList<Ticket> updated = new ArrayList<>();
            for (Ticket t : tickets) {
                if (t.getId() == ticketId) {
                    updated.add(new Ticket(
                            t.getId(),
                            t.getPassenger(),
                            t.getVehicle(),
                            t.getDateTime(),
                            t.getOrigin(),
                            newDestination
                    ));
                } else {
                    updated.add(t);
                }
            }
            ticketDAO.rewriteAll(updated);
            System.out.println("Ticket updated successfully");
            return true;
        } else {
            System.out.println("Ticket not found");
            return false;
        }
    }
}
