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

    public Ticket(int id, Pasajero pasajero, Vehicle vehiculo, LocalDateTime fechaHora, String origen, String destino) {
        this.id = id;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
        this.origen = origen;
        this.destino = destino;
    }
    
    
    
}
