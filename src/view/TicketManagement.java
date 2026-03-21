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

    private static void menuVehicles() {
        int option;
        do {
            System.out.println("\n--- GESTIÓN DE VEHÍCULOS ---");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Buscar vehículo por placa");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            option = readInt();
            switch (option) {
                case 1 -> registerVehicle();
                case 2 -> vehicleService.listarVehiculos();
                case 3 -> searchVehicle();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void registerVehicle() {
        System.out.println("\n  Tipo de vehículo:");
        System.out.println("  1. Buseta   (cap. 19, $8.000)");
        System.out.println("  2. MicroBus (cap. 25, $10.000)");
        System.out.println("  3. Bus      (cap. 45, $15.000)");
        System.out.print("  Tipo: ");
        int type = readInt();

        System.out.print("  Placa: ");
        String plate = scanner.nextLine().trim().toUpperCase();
        System.out.print("  Ruta: ");
        String route = scanner.nextLine().trim();

        Vehicle vehicle = switch (type) {
            case 1 -> new Buseta(plate, route);
            case 2 -> new MicroBus(plate, route);
            case 3 -> new Bus(plate, route);
            default -> null;
        };

        if (vehicle == null) {
            System.out.println("Tipo de vehículo inválido.");
            return;
        }
        vehicleService.registrarVehiculo(vehicle);
    }

    private static void searchVehicle() {
        System.out.print("  Placa: ");
        String plate = scanner.nextLine().trim().toUpperCase();
        Vehicle v = vehicleService.buscarVehiculo(plate);
        if (v != null) {
            v.printDetails();
        } else {
            System.out.println("No se encontró ningún vehículo con esa placa.");
        }
    }

    private static void menuDrivers() {
        int option;
        do {
            System.out.println("\n--- GESTIÓN DE CONDUCTORES ---");
            System.out.println("1. Registrar conductor");
            System.out.println("2. Listar conductores");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            option = readInt();
            switch (option) {
                case 1 -> registerDriver();
                case 2 -> listDrivers();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void registerDriver() {
        System.out.print("  Cédula: ");
        String id = scanner.nextLine().trim();
        System.out.print("  Nombre: ");
        String name = scanner.nextLine().trim();
        System.out.print("  Número de licencia: ");
        String licenseNumber = scanner.nextLine().trim();
        System.out.print("  Categoría (B1, B2, C1, C2): ");
        String category = scanner.nextLine().trim().toUpperCase();

        if (!category.matches("B1|B2|C1|C2")) {
            System.out.println("Categoría inválida. Debe ser B1, B2, C1 o C2.");
            return;
        }

        Driver driver = new Driver(licenseNumber, category, id, name);
        driverService.registerDriver(driver);
    }

    private static void listDrivers() {
        var drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }
        System.out.println("\n--- CONDUCTORES REGISTRADOS ---");
        for (var d : drivers) {
            System.out.println("  Cédula: " + d.getId()
                    + " | Nombre: " + d.getName()
                    + " | Licencia: " + d.getLicenseNumber()
                    + " | Categoría: " + d.getLicenseCategory());
        }
    }

    private static void menuPassengers() {
        int option;
        do {
            System.out.println("\n--- GESTIÓN DE PASAJEROS ---");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Listar pasajeros");
            System.out.println("3. Buscar pasajero por cédula");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            option = readInt();
            switch (option) {
                case 1 -> registerPassenger();
                case 2 -> listPassengers();
                case 3 -> searchPassenger();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void registerPassenger() {
        System.out.print("  Cédula: ");
        String id = scanner.nextLine().trim();
        System.out.print("  Nombre: ");
        String name = scanner.nextLine().trim();
        System.out.println("  Tipo de pasajero:");
        System.out.println("  1. Regular      (sin descuento)");
        System.out.println("  2. Estudiante   (15% de descuento)");
        System.out.println("  3. Adulto Mayor (30% de descuento)");
        System.out.print("  Tipo: ");
        int type = readInt();

        Passenger passenger = switch (type) {
            case 1 -> new RegularPassenger(id, name);
            case 2 -> new StudentPassenger(id, name);
            case 3 -> new SeniorPassenger(id, name);
            default -> null;
        };

        if (passenger == null) {
            System.out.println("Tipo de pasajero inválido.");
            return;
        }
        passengerService.registeredPassenger(passenger);
    }

    private static void listPassengers() {
        var passengers = passengerService.getAllPassengers();
        if (passengers.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }
        System.out.println("\n--- PASAJEROS REGISTRADOS ---");
        for (var p : passengers) {
            System.out.println("  Cédula: " + p.getId()
                    + " | Nombre: " + p.getName()
                    + " | Tipo: " + p.getClass().getSimpleName()
                    + " | Descuento: " + (int) (p.calculateDiscount() * 100) + "%");
        }
    }

    private static void searchPassenger() {
        System.out.print("  Cédula: ");
        String id = scanner.nextLine().trim();
        Passenger p = passengerService.findPassenger(id);
        if (p != null) {
            System.out.println("  Cédula: " + p.getId()
                    + " | Nombre: " + p.getName()
                    + " | Tipo: " + p.getClass().getSimpleName()
                    + " | Descuento: " + (int) (p.calculateDiscount() * 100) + "%");
        } else {
            System.out.println("No se encontró ningún pasajero con esa cédula.");
        }
    }

    private static void menuTickets() {
        int option;
        do {
            System.out.println("\n--- VENTA DE TICKETS ---");
            System.out.println("1. Vender ticket");
            System.out.println("2. Listar todos los tickets");
            System.out.println("3. Buscar ticket por ID");
            System.out.println("4. Eliminar ticket");
            System.out.println("5. Actualizar destino de ticket");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            option = readInt();
            switch (option) {
                case 1 -> sellTicket();
                case 2 -> ticketService.showTickets();
                case 3 -> searchTicket();
                case 4 -> deleteTicket();
                case 5 -> updateTicket();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void sellTicket() {
        System.out.print("  Cédula del pasajero: ");
        String passengerId = scanner.nextLine().trim();
        System.out.print("  Placa del vehículo: ");
        String plate = scanner.nextLine().trim().toUpperCase();
        System.out.print("  Origen: ");
        String origin = scanner.nextLine().trim();
        System.out.print("  Destino: ");
        String destination = scanner.nextLine().trim();

        ticketService.createTicket(passengerId, plate, origin, destination);
    }

    private static void searchTicket() {
        System.out.print("  ID del ticket: ");
        int id = readInt();
        Ticket t = ticketService.findTicketById(id);
        if (t != null) {
            t.printDetails();
        } else {
            System.out.println("No se encontró ningún ticket con ese ID.");
        }
    }

    private static void deleteTicket() {
        System.out.print("  ID del ticket a eliminar: ");
        int id = readInt();
        ticketService.deleteTicket(id);
    }

    private static void updateTicket() {
        System.out.print("  ID del ticket a actualizar: ");
        int id = readInt();
        System.out.print("  Nuevo destino: ");
        String newDestination = scanner.nextLine().trim();
        ticketService.updateTicket(id, newDestination);
    }


}
