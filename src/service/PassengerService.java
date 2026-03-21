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
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import model.RegularPassenger;
import model.SeniorPassenger;
import model.StudentPassenger;

public class PassengerService {
    private PassengerDAO dao = new PassengerDAO(); 
    
    public void registerPassenger(String id, String name, LocalDate birthDate, boolean isStudent){

        Passenger passenger;

        int age = Period.between(birthDate, LocalDate.now()).getYears();

    // 🔥 AQUÍ ESTÁ LA MAGIA
        if(age >= 60){
            passenger = new SeniorPassenger(id, name, birthDate);
        }
        else if(isStudent){
            passenger = new StudentPassenger(id, name, birthDate);
        }
        else{
            passenger = new RegularPassenger(id, name, birthDate);
        }

        if(dao.findPassengerById(id) == null){
            dao.save(passenger);
            System.out.println("Passenger registered successfully");
        }else{
            System.out.println("Passenger already exists");
        }
    }
    
    public ArrayList<Passenger> getAllPassengers() {
        return dao.list();
    }
    
    public Passenger findPassenger(String id) {
        return dao.findPassengerById(id);
    }
}
