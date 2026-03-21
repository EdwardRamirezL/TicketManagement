package view;

import model.*;
import service.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketManagement {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VehiculoService vehicleService = new VehiculoService();
    private static final PassengerService passengerService = new PassengerService();
    private static final DriverService driverService = new DriverService();
    private static final TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        int option;
        do {
            printMainMenu();
            option = readInt();
            switch (option) {
                case 1 -> menuVehicles();
                case 2 -> menuDrivers();
                case 3 -> menuPassengers();
                case 4 -> menuTickets();
                case 5 -> menuStatistics();
                case 0 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 0);
    }

    private static void printMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     TransCesar S.A.S. – Sistema de     ║");
        System.out.println("║         Gestión de Tickets             ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Gestión de Vehículos               ║");
        System.out.println("║  2. Gestión de Conductores             ║");
        System.out.println("║  3. Gestión de Pasajeros               ║");
        System.out.println("║  4. Venta de Tickets                   ║");
        System.out.println("║  5. Consultas y Estadísticas           ║");
        System.out.println("║  0. Salir                              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

}
