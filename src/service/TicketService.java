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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Ticket;
import model.Passenger;
import model.Vehicle;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
    
    private static final List<MonthDay> FESTIVOS = Arrays.asList(
        MonthDay.of(1, 1),
        MonthDay.of(5, 1),
        MonthDay.of(7, 20),
        MonthDay.of(8, 7),
        MonthDay.of(12, 8),
        MonthDay.of(12, 25)
    );
    
    private boolean isFestivo(LocalDate date) {
        return FESTIVOS.contains(MonthDay.of(date.getMonth(), date.getDayOfMonth()));
    }
    
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

        ArrayList<Ticket> allTickets = ticketDAO.list();

        long occupiedSeats = allTickets.stream()
                .filter(t -> t.getVehicle().getPlate().equalsIgnoreCase(plate))
                .count();
        if (occupiedSeats >= vehicle.getMaxCapacity()) {
            System.out.println("El vehículo no tiene asientos disponibles (capacidad: " + vehicle.getMaxCapacity() + ").");
            return;
        }

        LocalDate today = LocalDate.now();
        long ticketsToday = allTickets.stream()
                .filter(t -> t.getPassenger().getId().equalsIgnoreCase(passengerId))
                .filter(t -> t.getDateTime().toLocalDate().equals(today))
                .count();

        if (ticketsToday >= 3) {
            System.out.println("Venta rechazada: el pasajero ya tiene " + ticketsToday + " ticket(s) para hoy.");
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
