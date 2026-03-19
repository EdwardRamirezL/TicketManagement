/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dimas
 */
public class SeniorPassenger extends Passenger {

    public SeniorPassenger(String id, String name) {
        super(id, name);
    }

    @Override
    public double calculateDiscount() {
        return 0.30;
    }
}
