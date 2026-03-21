/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ASUS
 */


import model.Route;
import java.io.*;
import java.util.ArrayList;

public class RouteDAO {

    private final String fileName = "routes.txt";
    
    public void saveRoute(Route r){

    try{

        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(
            r.getCode() + ";" +
            r.getOrigin() + ";" +
            r.getDestination() + ";" +
            r.getDistance() + ";" +
            r.getTime()
        );

        pw.close();

    }catch(IOException e){

        System.out.println("Error saving route");

    }

}

    
    

    public ArrayList<Route> loadRoutes(){

        ArrayList<Route> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){

            String line;

            while((line = br.readLine()) != null){

                if(line.trim().isEmpty()) continue;

                String[] data = line.split(";");

                list.add(new Route(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4])
                ));
            }

        }catch(IOException e){
            System.out.println("Archivo de rutas no encontrado");
        }

        return list;
    }

    
    public Route getRouteByCode(String code){

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){

            String line;

            while((line = br.readLine()) != null){

                if(line.trim().isEmpty()) continue;

                String[] data = line.split(";");

                if(data[0].equalsIgnoreCase(code)){

                    return new Route(
                            data[0],
                            data[1],
                            data[2],
                            Double.parseDouble(data[3]),
                            Integer.parseInt(data[4])
                    );
                }
            }

        }catch(IOException e){
            System.out.println("Error leyendo las");
        }

        return null;
    }
}
