/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author dimas
 */
public class StudentPassenger extends Passenger {

    public StudentPassenger(String id, String name, LocalDate birthDate) {
        super(id, name, birthDate);
    }

 

    

    @Override
    public double calculateDiscount() {
        return 0.15;
    }
}
