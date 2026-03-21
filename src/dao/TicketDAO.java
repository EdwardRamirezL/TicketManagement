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
import model.Passenger;
import model.Vehicle;

public class TicketDAO {
    private final String file = "tickets.txt";
    private PassengerDAO passengerDAO = new PassengerDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void save(Ticket t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(t.getId() + ";" +
                    t.getPassenger().getId() + ";" +
                    t.getVehicle().getPlate() + ";" +
                    t.getDateTime() + ";" +
                    t.getOrigin() + ";" +
                    t.getDestination() + ";" +
                    t.calculateTotal());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteAll(ArrayList<Ticket> tickets) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Ticket t : tickets) {
                bw.write(t.getId() + ";" +
                        t.getPassenger().getId() + ";" +
                        t.getVehicle().getPlate() + ";" +
                        t.getDateTime() + ";" +
                        t.getOrigin() + ";" +
                        t.getDestination() + ";" +
                        t.calculateTotal());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ticket> list() {
    ArrayList<Ticket> tickets = new ArrayList<>();
    File f = new File(file);
    if (!f.exists() || f.length() == 0) return tickets;
    
    ArrayList<Passenger> passengers = passengerDAO.list();
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] data = line.split(";");
            if (data.length < 7) continue;
            
            int id = Integer.parseInt(data[0]);
            String passengerId = data[1];
            String vehiclePlate = data[2];
            LocalDateTime dateTime = LocalDateTime.parse(data[3], formatter);
            String origin = data[4];
            String destination = data[5];

            Passenger passenger = null;
            for (Passenger p : passengers) {
                if (p.getId().equals(passengerId)) {
                    passenger = p;
                    break;
                }
            }
            double total = Double.parseDouble(data[6]);
            Vehicle vehicle = vehicleDAO.getVehicleByPlate(vehiclePlate);
            if (passenger != null && vehicle != null) {
                tickets.add(new Ticket(id, passenger, vehicle, dateTime, origin, destination, total));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return tickets;
}
}