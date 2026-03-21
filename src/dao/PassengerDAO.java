/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author dimas
 */
import model.Passenger;
import model.RegularPassenger;
import model.StudentPassenger;
import model.SeniorPassenger;

import java.io.*;
import java.util.ArrayList;

public class PassengerDAO {
    private final String file = "passengers.txt";
    
    public boolean exists(String id) {
        for (Passenger p : list()) {
            if (p.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void save(Passenger p) {
       if (exists(p.getId())) {
            System.out.println("El pasajero con ID " + p.getId() + " ya existe.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String type = p.getClass().getSimpleName();
            bw.write(p.getId() + ";" + p.getName() + ";" + type);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Passenger> list() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        File f = new File(file);
        if (!f.exists()) return passengers;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");

                if (data.length < 3) continue;

                String id = data[0];
                String name = data[1];
                String type = data[2];

                switch (type) {
                    case "RegularPassenger":
                        passengers.add(new RegularPassenger(id, name));
                        break;
                    case "StudentPassenger":
                        passengers.add(new StudentPassenger(id, name));
                        break;
                    case "SeniorPassenger":
                        passengers.add(new SeniorPassenger(id, name));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passengers;
    }
    
    public Passenger findPassengerById(String id) {

    for (Passenger p : getAllPassengers()) {
        if (p.getId().equalsIgnoreCase(id)) {
            return p;
        }
    }

    return null;
}
    
    public ArrayList<Passenger> getAllPassengers() {
    return list();
}

    public Passenger findPassenger(String id) {
    return findPassengerById(id);
    }
}