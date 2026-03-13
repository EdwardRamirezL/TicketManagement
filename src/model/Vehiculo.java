/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public abstract class Vehiculo implements Imprimible {

    protected String placa;
    protected String ruta;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected double tarifaBase;

    public Vehiculo(String placa, String ruta) {
        this.placa = placa;
        this.ruta = ruta;
        this.pasajerosActuales = 0;
    }

    public boolean hayCupos() {
        return pasajerosActuales < capacidadMaxima;
    }

    public int cuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    public void subirPasajero() {
        if(hayCupos()){
            pasajerosActuales++;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public String getRuta() {
        return ruta;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

}
