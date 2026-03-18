/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author dimas
 */
import model.Ticket;

import java.io.*;
import java.util.ArrayList;

public class TicketDAO {
    private final String archivo = "tickets.txt";
    
    public void guardar(Ticket t){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))){
            bw.write(t.getId() + ";" + 
                    t.getPasajero().getCedula()+ ";" +
                    t.getVehiculo().getPlate() + ";" +
                    t.getFechaHora() + ";" + 
                    t.getOrigen() + ";" + 
                    t.getDestino() + ";" + 
                    t.calcularTotal());
            bw.newLine();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
