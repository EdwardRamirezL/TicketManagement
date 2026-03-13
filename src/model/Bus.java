/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public class Bus extends Vehiculo {

    public Bus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 45;
        this.tarifaBase = 15000;
    }

    @Override
    public void imprimirDetalle() {

        System.out.println("Tipo: Bus");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Capacidad: " + capacidadMaxima);
        System.out.println("Cupos disponibles: " + cuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);

    }

}
