/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PassengerDAO;
import dao.ReserveDAO;
import dao.TicketDAO;
import dao.VehicleDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import model.Passenger;
import model.Reserve;
import model.Vehicle;

/**
 *
 * @author dimas
 */
public class ReserveService {
    private ReserveDAO reserveDAO = new ReserveDAO(); 
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
    private TicketDAO ticketDAO = new TicketDAO();
    
     private static final List<MonthDay> FESTIVOS = Arrays.asList(
        MonthDay.of(1,  1),
        MonthDay.of(5,  1),
        MonthDay.of(7, 20),
        MonthDay.of(8,  7),
        MonthDay.of(12, 8),
        MonthDay.of(12, 25)
    );
     
     private boolean isFestivo(LocalDate date){
         return FESTIVOS.contains(MonthDay.of(date.getMonth(), date.getDayOfMonth()));
     }
     
     private long getOccupiedSeats(String plate) {
        long tickets = ticketDAO.list().stream()
                .filter(t -> t.getVehicle().getPlate().equalsIgnoreCase(plate))
                .count();
 
        long activeReserves = reserveDAO.getActiveReservations().stream()
                .filter(r -> r.getVehicle().getPlate().equalsIgnoreCase(plate))
                .count();
 
        return tickets + activeReserves;
    }
     
     public void createReserve(String passengerId, String plate, LocalDate travelDate) {
 
       
        Passenger passenger = passengerDAO.findPassengerById(passengerId);
        if (passenger == null) {
            System.out.println("Error: passenger not found.");
            return;
        }
 
        Vehicle vehicle = vehicleDAO.getVehicleByPlate(plate);
        if (vehicle == null) {
            System.out.println("Error: vehicle not found.");
            return;
        }
 
        
        if (travelDate.isBefore(LocalDate.now())) {
            System.out.println("Error: travel date cannot be in the past.");
            return;
        }
 
        
        if (getOccupiedSeats(plate) >= vehicle.getMaxCapacity()) {
            System.out.println("Error: vehicle has no available seats (capacity: "
                    + vehicle.getMaxCapacity() + ").");
            return;
        }
 
        
        boolean duplicate = reserveDAO.getActiveReservations().stream()
                .anyMatch(r -> r.getPassenger().getId().equalsIgnoreCase(passengerId)
                        && r.getVehicle().getPlate().equalsIgnoreCase(plate)
                        && r.getTravelDate().equals(travelDate));
 
        if (duplicate) {
            System.out.println("Error: passenger already has an active reservation"
                    + " for this vehicle on " + travelDate + ".");
            return;
        }
 
        
        String codigo = reserveDAO.getNextCodigo();
        Reserve reserve = new Reserve(codigo, passenger, vehicle,
                LocalDateTime.now(), travelDate);
 
        reserveDAO.save(reserve);
        System.out.println("Reservation created successfully. Code: " + codigo);
    }
    
}
