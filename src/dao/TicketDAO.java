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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Pasajero;
import model.Vehicle;

public class TicketDAO {
    private final String archivo = "tickets.txt";
    private PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
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
    
    public ArrayList<Ticket> listar() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Pasajero> pasajeros = pasajeroDAO.listar();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String cedulaPasajero = datos[1];
                String placaVehiculo = datos[2];
                LocalDateTime fechaHora = LocalDateTime.parse(datos[3], formatter);
                String origen = datos[4];
                String destino = datos[5];
                double total = Double.parseDouble(datos[6]);

                Pasajero pasajero = null;
                for (Pasajero p : pasajeros) {
                    if (p.getCedula().equals(cedulaPasajero)) {
                        pasajero = p;
                        break;
                    }
                }

                Vehicle vehiculo = vehicleDAO.getVehicleByPlate(placaVehiculo);

                if (pasajero != null && vehiculo != null) {
                    tickets.add(new Ticket(id, pasajero, vehiculo, fechaHora, origen, destino));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
