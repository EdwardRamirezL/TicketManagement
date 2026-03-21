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


public class VehicleService {

    private VehicleDAO dao = new VehicleDAO();
    private ArrayList<Vehicle> vehicleList;

    public VehicleService() {

        vehicleList = dao.loadVehicles();

    }

    public void registerVehicle(Vehicle v){

        for(Vehicle vehicle : vehicleList){

            if(vehicle.getPlate().equalsIgnoreCase(v.getPlate())){

                System.out.println("ERROR: A vehicle with this plate already exists");
                return;

            }

        }

        vehicleList.add(v);
        dao.saveVehicle(v);

        System.out.println("Vehicle registered successfully");

    }

    public void listVehicles(){

        for(Vehicle v : vehicleList){

            v.printDetails();
            System.out.println("-------------------------");

        }

    }

    public Vehicle findVehicle(String plate){

        for(Vehicle v : vehicleList){

            if(v.getPlate().equalsIgnoreCase(plate))
                return v;

        }

        return null;

    }

}


