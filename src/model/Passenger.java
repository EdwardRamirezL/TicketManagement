/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dimas
 */
public abstract class Passenger extends Person {

    public Passenger(String id, String name) {
        super(id, name);
    }
    
    public abstract double calculateDiscount();
}
