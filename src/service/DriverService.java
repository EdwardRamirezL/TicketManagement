/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author dimas
 */


import dao.DriverDAO;
import model.Driver;

import java.util.List;

public class DriverService {
    private DriverDAO dao = new DriverDAO();
    
    public void registerDriver(Driver driver){
        if(dao.findDriverById(driver.getId())==null){
            dao.saveDriver(driver);
            System.out.println("Driver registered successfully");
        }else{
            System.out.println("Driver already exists");
        }
    }
}
