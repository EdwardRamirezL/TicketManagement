/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author dimas
 */
import dao.PassengerDAO;
import java.util.ArrayList;
import model.Passenger;

import java.util.List;

public class PassengerService {
    private PassengerDAO dao = new PassengerDAO(); 
    
    public void registeredPassenger(Passenger passenger){
        if(dao.findPassengerById(passenger.getId())!= null){
            dao.save(passenger); 
            System.out.println("Passenger registered Successfully");
        }else{
            System.out.println("Passenger already exists");
        }
    }
    
    public ArrayList<Passenger> getAllPassengers() {
        return dao.list();
    }
}
