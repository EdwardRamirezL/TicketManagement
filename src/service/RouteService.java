/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ASUS
 */


import dao.RouteDAO;
import model.Route;

import java.util.ArrayList;
import model.Vehicle;

public class RouteService {

    private RouteDAO dao = new RouteDAO();
    private ArrayList<Route> routes;

    public RouteService(){
        routes = dao.loadRoutes();
    }

    public void listRoutes(){

        for(Route r : routes){

            System.out.println(
                r.getCode() + " | " +
                r.getOrigin() + " -> " +
                r.getDestination()
            );
        }
    }

    public Route findRouteByCode(String code){

        for(Route r : routes){
            if(r.getCode().equalsIgnoreCase(code))
                return r;
        }

        return null;
    }
    
    public Route getAvailableRoute(){

    for(Route r : routes){
        return r; 
    }

    return null;
}
 
    public Route getRouteForVehicle(Vehicle v){

    for(Route r : routes){

        
        if(v.hasAvailableSeats()){
            return r;
        }

    }

    return null;
}

    
    
    
    
}

