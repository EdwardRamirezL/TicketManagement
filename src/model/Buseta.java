/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public class Buseta extends Vehiculo {

    public Buseta(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 19;
        this.tarifaBase = 8000;
    }

    @Override
    public void imprimirDetalle() {

        System.out.println("Tipo: Buseta");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Capacidad: " + capacidadMaxima);
        System.out.println("Cupos disponibles: " + cuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);

    }

}

