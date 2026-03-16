/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ASUS
 */


package dao;
import java.io.*;
import java.util.ArrayList;
import model.*;
import model.Vehicle;

import java.io.FileWriter;

import java.io.PrintWriter;

import java.io.IOException;



public class VehicleDAO {

    private final String fileName = "vehicles.txt";

    public void saveVehicle(Vehicle v){

        try{

            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);

            String type = v.getClass().getSimpleName();

            pw.println(v.getPlate() + ";" + v.getRoute() + ";" + type);

            pw.close();

        }catch(IOException e){

            System.out.println("Error saving vehicle");

        }

    }

    public ArrayList<Vehicle> loadVehicles(){

        ArrayList<Vehicle> list = new ArrayList<>();

        try{

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(";");

                String plate = data[0];
                String route = data[1];
                String type = data[2];

                Vehicle v = null;

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

            System.out.println("Vehicle file not found. It will be created later.");

        }

        return list;

    }

}
