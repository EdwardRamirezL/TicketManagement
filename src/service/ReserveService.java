/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PassengerDAO;
import dao.ReserveDAO;
import dao.TicketDAO;
import dao.VehicleDAO;

/**
 *
 * @author dimas
 */
public class ReserveService {
    private ReserveDAO reserveDAO = new ReserveDAO(); 
    private PassengerDAO passengerDAO = new PassengerDAO(); 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
    private TicketDAO ticketDAO = new TicketDAO();
}
