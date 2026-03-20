/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author dimas
 */
import model.Driver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DriverDAO {
    private final String file = "drivers.txt"; 
    
    public void saveDriver(Driver driver){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(driver.getId() + ";" +
                     driver.getName() + ";" + 
                     driver.getLicenseNumber() + ";" + 
                     driver.getLicenseCategory());
            bw.newLine();
        }catch(IOException e){
            System.out.println("Error saving driver: " + e.getMessage());
        }
    }
    
    public List<Driver> getAllDrivers(){
        List<Driver> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line; 
            while((line = br.readLine())!= null){
                String[] data = line.split(";");
                Driver driver = new Driver(
                data[0],
                data[1],
                data[2],
                data[3]
                );
                list.add(driver); 
            }
        }catch(IOException e){
            System.out.println("Error saving drivers: " + e.getMessage());
        }
        return list;
    }
}
