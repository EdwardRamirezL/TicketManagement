/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PassengerDAO;
import dao.ReserveDAO;
import dao.TicketDAO;
import dao.VehicleDAO;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;

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
    
}
