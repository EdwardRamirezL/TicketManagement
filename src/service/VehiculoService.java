/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ASUS
 */


import dao.VehiculoDAO;
import model.Vehiculo;

import java.util.ArrayList;

public class VehiculoService {

    private VehiculoDAO dao = new VehiculoDAO();
    private ArrayList<Vehiculo> listaVehiculos;

    public VehiculoService() {

        listaVehiculos = dao.cargarVehiculos();

    }

    public void registrarVehiculo(Vehiculo v){

        for(Vehiculo veh : listaVehiculos){

            if(veh.getPlaca().equalsIgnoreCase(v.getPlaca())){

                System.out.println("ERROR: ya existe un vehículo con esa placa");
                return;

            }

        }

        listaVehiculos.add(v);
        dao.guardarVehiculo(v);

        System.out.println("Vehículo registrado correctamente");

    }

    public void listarVehiculos(){

        for(Vehiculo v : listaVehiculos){

            v.imprimirDetalle();
            System.out.println("------------------------");

        }

    }

    public Vehiculo buscarVehiculo(String placa){

        for(Vehiculo v : listaVehiculos){

            if(v.getPlaca().equalsIgnoreCase(placa))
                return v;

        }

        return null;

    }

}

