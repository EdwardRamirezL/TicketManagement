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
import model.Ticket;
import model.Passenger;
import model.Vehicle;

import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
}
