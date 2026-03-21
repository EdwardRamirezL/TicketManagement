/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ASUS
 */



import java.io.*;
import java.util.ArrayList;
import model.*;
import model.Vehicle;

import java.io.FileWriter;

import java.io.PrintWriter;

import java.io.IOException;



public class VehicleDAO {

    private final String fileName = "vehicles.txt";
    private RouteDAO routeDAO = new RouteDAO();

    public void saveVehicle(Vehicle v){

        try{

            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);

            String type = v.getClass().getSimpleName();

            pw.println(v.getPlate() + ";" + v.getRoute().getCode() + ";" + type);

            pw.close();

        }catch(IOException e){

            System.out.println("Error guardando el vehiculo");

        }

    }

    public ArrayList<Vehicle> loadVehicles(){

        ArrayList<Vehicle> list = new ArrayList<>();

        try{

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = br.readLine()) != null){
                
                if(line.trim().isEmpty()) continue;

                String[] data = line.split(";");

                String plate = data[0];
                String routeCode = data[1];
                Route route = routeDAO.getRouteByCode(routeCode);
                String type = data[2];

                Vehicle v = null;
                
                if(route == null){
                    System.out.println("Ruta no encontrada: " + routeCode);
                    continue;
                }

                if(type.equals("Buseta"))
                    v = new Buseta(plate, route);

                if(type.equals("MicroBus"))
                    v = new MicroBus(plate, route);

                if(type.equals("Bus"))
                    v = new Bus(plate, route);

                list.add(v);

            }

            br.close();

        }catch(Exception e){

            System.out.println("Archivo de vehiculo no encontrado. Se creara mas tarde");

        }

        return list;

    }


    
    
    public Vehicle getVehicleByPlate(String plate){

    try{

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while((line = br.readLine()) != null){
            
            if(line.trim().isEmpty()) continue;

            String[] data = line.split(";");

            String filePlate = data[0];
            String routeCode = data[1];
            Route route = routeDAO.getRouteByCode(routeCode);
            String type = data[2];
            
            if(route == null){
                System.out.println("Ruta no encontrada: " + routeCode);
                continue;
            }


            if(filePlate.equalsIgnoreCase(plate)){

                br.close();

                if(type.equals("Buseta"))
                    return new Buseta(filePlate, route);

                if(type.equals("MicroBus"))
                    return new MicroBus(filePlate, route);

                if(type.equals("Bus"))
                    return new Bus(filePlate, route);
                
                

            }

        }

        br.close();

    }catch(IOException e){

        System.out.println("Error al leer el archivo");

    }

    return null; 
}

    
    

}
