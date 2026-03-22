package dao;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Passenger;
import model.Reserva;
import model.ReservationStatus;
import model.Vehicle;

public class ReservaDAO {

    private final String file = "reservas.txt";
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private PassengerDAO passengerDAO = new PassengerDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();


    public void save(Reserva r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(serialize(r));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteAll(ArrayList<Reserva> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Reserva r : reservas) {
                bw.write(serialize(r));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reserva> list() {
        ArrayList<Reserva> result = new ArrayList<>();
        File f = new File(file);
        if (!f.exists() || f.length() == 0) return result;

        ArrayList<Passenger> passengers = passengerDAO.list();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");
                if (data.length < 6) continue;

                String codigo = data[0];
                String passengerId = data[1];
                String vehiclePlate = data[2];
                LocalDateTime creationDate = LocalDateTime.parse(data[3], dtFormatter);
                LocalDate travelDate = LocalDate.parse(data[4], dateFormatter);
                ReservationStatus status = ReservationStatus.valueOf(data[5]);

                Passenger passenger = null;
                for (Passenger p : passengers) {
                    if (p.getId().equals(passengerId)) {
                        passenger = p;
                        break;
                    }
                }

                Vehicle vehicle = vehicleDAO.getVehicleByPlate(vehiclePlate);

                if (passenger != null && vehicle != null) {
                    result.add(new Reserva(codigo, passenger, vehicle,
                                           creationDate, travelDate, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Reserva findByCodigo(String codigo) {
        for (Reserva r : list()) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Reserva> getActiveReservations() {
        ArrayList<Reserva> activas = new ArrayList<>();
        for (Reserva r : list()) {
            if (r.getStatus() == ReservationStatus.ACTIVA) {
                activas.add(r);
            }
        }
        return activas;
    }

    public ArrayList<Reserva> getByPassenger(String passengerId) {
        ArrayList<Reserva> history = new ArrayList<>();
        for (Reserva r : list()) {
            if (r.getPassenger().getId().equalsIgnoreCase(passengerId)) {
                history.add(r);
            }
        }
        return history;
    }

    /**
     * Recorre todas las reservas activas, marca como CANCELADA las que han
     * superado las 24 horas desde su creación y reescribe el archivo.
     *
     * @return cantidad de reservas canceladas por vencimiento
     */
    public int expireOldReservations() {
        ArrayList<Reserva> all = list();
        int count = 0;
        for (Reserva r : all) {
            if (r.isExpired()) {
                r.cancel();
                count++;
            }
        }
        if (count > 0) {
            rewriteAll(all);
        }
        return count;
    }

    /**
     * Genera el siguiente código de reserva con el formato RES-N,
     * donde N es el máximo número existente + 1.
     */
    public String getNextCodigo() {
        ArrayList<Reserva> all = list();
        int max = 0;
        for (Reserva r : all) {
            String cod = r.getCodigo();
            if (cod.startsWith("RES-")) {
                try {
                    int n = Integer.parseInt(cod.substring(4));
                    if (n > max) max = n;
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return "RES-" + (max + 1);
    }

    private String serialize(Reserva r) {
        return r.getCodigo() + ";" +
               r.getPassenger().getId() + ";" +
               r.getVehicle().getPlate() + ";" +
               r.getCreationDate().format(dtFormatter) + ";" +
               r.getTravelDate().format(dateFormatter) + ";" +
               r.getStatus().name();
    }
}
