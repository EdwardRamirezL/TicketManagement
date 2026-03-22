package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserve implements Printable {

    private String codigo;
    private Passenger passenger;
    private Vehicle vehicle;
    private LocalDateTime creationDate;
    private LocalDate travelDate;
    private ReservationStatus status;

    public Reserve(String codigo, Passenger passenger, Vehicle vehicle,
                   LocalDateTime creationDate, LocalDate travelDate) {
        this.codigo = codigo;
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.creationDate = creationDate;
        this.travelDate = travelDate;
        this.status = ReservationStatus.ACTIVA;
    }

    public Reserve(String codigo, Passenger passenger, Vehicle vehicle,
                   LocalDateTime creationDate, LocalDate travelDate,
                   ReservationStatus status) {
        this.codigo = codigo;
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.creationDate = creationDate;
        this.travelDate = travelDate;
        this.status = status;
    }

    public boolean isExpired() {
        return status == ReservationStatus.ACTIVA &&
               LocalDateTime.now().isAfter(creationDate.plusHours(24));
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELADA;
    }

    public void convert() {
        this.status = ReservationStatus.CONVERTIDA;
    }

    public String getCodigo() {
        return codigo;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public void printDetails() {
        System.out.println("----- RESERVA -----");
        System.out.println("Código:       " + codigo);
        System.out.println("Pasajero:     " + passenger.getName() + " (" + passenger.getId() + ")");
        System.out.println("Vehículo:     " + vehicle.getPlate() + " - " + vehicle.getClass().getSimpleName());
        System.out.println("Fecha creación: " + creationDate);
        System.out.println("Fecha viaje:  " + travelDate);
        System.out.println("Estado:       " + status);
        System.out.println("-------------------");
    }
}
