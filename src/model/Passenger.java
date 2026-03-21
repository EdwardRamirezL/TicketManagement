/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author dimas
 */
public abstract class Passenger extends Person {
    
    protected LocalDate birthDate;
    protected double discount;

    public Passenger(String id, String name, LocalDate birthDate) {
        super(id, name);
        this.birthDate = birthDate;
    }
    
    public boolean isSenior(){

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age >= 60;

    }
    
    public abstract double calculateDiscount();
}
