/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author dimas
 */
public class Ticket implements Printable, Calculable{
    private int id; 
    private Pasajero pasajero; 
    private Vehicle vehiculo; 
    private LocalDateTime fechaHora; 
    private String origen; 
    private String destino; 
    
}
