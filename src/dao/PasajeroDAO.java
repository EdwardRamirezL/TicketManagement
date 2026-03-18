/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author dimas
 */
import model.Pasajero;
import model.PasajeroRegular;
import model.PasajeroEstudiante;
import model.PasajeroAdultoMayor;

import java.io.*;
import java.util.ArrayList;


public class PasajeroDAO {
    private final String archivo = "pasajeros.txt";


public void guardar(Pasajero p){
try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))){
  String tipo = p.getClass().getSimpleName();
  bw.write(p.getCedula() + ";" + p.getNombre() + ";" + tipo); 
  bw.newLine();
}catch(IOException e){
  e.printStackTrace();
}
}

public ArrayList<Pasajero> listar(){
    ArrayList<Pasajero> pasajeros = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
        String linea; 
        while((linea = br.readLine())!= null){
            String[] datos = linea.split(";");
            String cedula = datos[0]; 
            String nombre = datos[1]; 
            String tipo = datos[2]; 
            switch(tipo){
                case "PasajeroRegular": 
                    pasajeros.add(new PasajeroRegular(cedula, nombre));
                    break;
                case "PasajeroEstudiante": 
                    pasajeros.add(new PasajeroEstudiante(cedula, nombre)); 
                    break; 
                case "PasajeroAdultoMayor": 
                    pasajeros.add(new PasajeroAdultoMayor(cedula, nombre)); 
                    break; 
            }
        }
    }catch(IOException e){
        e.printStackTrace();
    }
    return pasajeros; 
}
}