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

    public int getId() {
        return id;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehicle getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
    
    @Override
    public double calcularTotal(){
        double tarifaBase = vehiculo.getBaseFare();
        double descuento = pasajero.calcularDescuento(); 
        return tarifaBase * (1 - descuento);
    }
    
    @Override
    public void printDetails(){
        System.out.println("-----TICKET-----"); 
        System.out.println("ID: " + id); 
        System.out.println("Pasajero: " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehiculo: " + vehiculo.getPlate() + " - " + vehiculo.getClass().getSimpleName());
        System.out.println("Ruta: " + vehiculo.getRoute());
        System.out.println("Fecha/Hora: " + fechaHora);
        System.out.println("Origen : " + origen + "| Destino: " + destino);
        System.out.println(" Valor total: $%.2f%n" + calcularTotal());
        System.out.println("-----------------");
        
    }
    
}
