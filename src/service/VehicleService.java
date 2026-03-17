/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ASUS
 */


import dao.VehicleDAO;
import model.Vehicle;

import java.util.ArrayList;

public class VehiculoService {

    private VehicleDAO dao = new VehicleDAO();
    private ArrayList<Vehicle> listaVehiculos;

    public VehiculoService() {

        listaVehiculos = dao.cargarVehiculos();

    }

    public void registrarVehiculo(Vehicle v){

        for(Vehicle veh : listaVehiculos){

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

        for(Vehicle v : listaVehiculos){

            v.imprimirDetalle();
            System.out.println("------------------------");

        }

    }

    public Vehicle buscarVehiculo(String placa){

        for(Vehicle v : listaVehiculos){

            if(v.getPlaca().equalsIgnoreCase(placa))
                return v;

        }

        return null;

    }

}

