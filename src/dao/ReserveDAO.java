package dao;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Passenger;
import model.Reserve;
import model.ReservationStatus;
import model.Vehicle;

public class ReserveDAO {

    private final String file = "reservas.txt";
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private PassengerDAO passengerDAO = new PassengerDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();


    public void save(Reserve r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(serialize(r));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteAll(ArrayList<Reserve> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Reserve r : reservas) {
                bw.write(serialize(r));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reserve> list() {
        ArrayList<Reserve> result = new ArrayList<>();
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
                    result.add(new Reserve(codigo, passenger, vehicle,
                                           creationDate, travelDate, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Reserve findByCodigo(String codigo) {
        for (Reserve r : list()) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Reserve> getActiveReservations() {
        ArrayList<Reserve> activas = new ArrayList<>();
        for (Reserve r : list()) {
            if (r.getStatus() == ReservationStatus.ACTIVA) {
                activas.add(r);
            }
        }
        return activas;
    }

    public ArrayList<Reserve> getByPassenger(String passengerId) {
        ArrayList<Reserve> history = new ArrayList<>();
        for (Reserve r : list()) {
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
        ArrayList<Reserve> all = list();
        int count = 0;
        for (Reserve r : all) {
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
        ArrayList<Reserve> all = list();
        int max = 0;
        for (Reserve r : all) {
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

    private String serialize(Reserve r) {
        return r.getCodigo() + ";" +
               r.getPassenger().getId() + ";" +
               r.getVehicle().getPlate() + ";" +
               r.getCreationDate().format(dtFormatter) + ";" +
               r.getTravelDate().format(dateFormatter) + ";" +
               r.getStatus().name();
    }
}
