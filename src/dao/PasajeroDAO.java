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
}