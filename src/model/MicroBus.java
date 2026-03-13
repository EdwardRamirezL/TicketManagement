/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */



public class MicroBus extends Vehiculo {

    public MicroBus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 25;
        this.tarifaBase = 10000;
    }

    @Override
    public void imprimirDetalle() {

        System.out.println("Tipo: MicroBus");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Capacidad: " + capacidadMaxima);
        System.out.println("Cupos disponibles: " + cuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);

    }

}
