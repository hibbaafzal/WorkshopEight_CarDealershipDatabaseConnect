package com.ps;

import com.ps.dao.LeaseDao;
import com.ps.dao.SalesDao;
import com.ps.dao.VehicleDao;
import com.ps.models.Lease;
import com.ps.models.Sales;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static VehicleDao vehicleDao;
    private static SalesDao salesDao;
    private static LeaseDao leaseDao;
    private static Scanner scanner = new Scanner(System.in);

    public static void init(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership_db");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
        vehicleDao = new VehicleDao(basicDataSource);
        leaseDao = new LeaseDao(basicDataSource);
        salesDao = new SalesDao(basicDataSource);
    }

    public static void display(String[] args) {
        init(args);

        int command;
        do {
            System.out.println("\nWelcome to Our Dealership");
            System.out.println("1. Search Vehicles");
            System.out.println("2. Manage Vehicles");
            System.out.println("3. Sales and Leases");
            System.out.println("0. Exit");

            command = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (command) {
                case 1:
                    searchVehicles();
                    break;
                case 2:
                    manageVehicles();
                    break;
                case 3:
                    manageSalesAndLeases();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (command != 0);
    }

    private static void searchVehicles() {
        int choice;
        do {
            System.out.println("\nSearch Vehicles:");
            System.out.println("\t1. Get all Vehicles");
            System.out.println("\t2. Make/Model");
            System.out.println("\t3. Year Range");
            System.out.println("\t4. Color");

            System.out.println("0. Go Back");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Vehicle> allVehicles = vehicleDao.getAllVehicles();
                    for (Vehicle vehicle: allVehicles) {
                        System.out.println(vehicle);
                    }

                    break;

                case 2:
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    List<Vehicle> vehiclesByMakeModel = vehicleDao.getVehiclesByMakeModel(make, model);
                    vehiclesByMakeModel.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter start year: ");
                    int startYear = scanner.nextInt();
                    System.out.print("Enter end year: ");
                    int endYear = scanner.nextInt();
                    List<Vehicle> vehiclesByYearRange = vehicleDao.getVehiclesByYearRange(startYear, endYear);
                    vehiclesByYearRange.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();
                    List<Vehicle> vehiclesByColor = vehicleDao.getVehiclesByColor(color);
                    vehiclesByColor.forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private static void manageVehicles() {
        int choice;
        do {
            System.out.println("What would you like to do?:");
            System.out.println("\t1. Add Vehicle");
            System.out.println("\t2. Remove Vehicle");
            System.out.println("\t0. Go Back");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();


                    Vehicle vehicle = new Vehicle(vin, make, model, year, color);
                    vehicleDao.createVehicle(vehicle);
                    System.out.println("Vehicle added successfully.");
                    break;

                case 2:
                    System.out.print("Enter VIN to delete: ");
                    String deleteVin = scanner.nextLine();
                    vehicleDao.deleteVehicle(deleteVin);
                    System.out.println("Vehicle removed successfully.");
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private static void manageSalesAndLeases() {
        int choice;
        do {
            System.out.println("\nManage Sales and Leases:");
            System.out.println("\t1. Add a Sale");
            System.out.println("\t2. Delete a Sale");
            System.out.println("\t3. View all Sales");
            System.out.println("\t4. Add a Lease");
            System.out.println("\t5. Delete a Lease");
            System.out.println("\t6. View all Leases");
            System.out.println("\t0. Go Back");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter buyer name: ");
                    String buyerName = scanner.nextLine();
                    System.out.print("Enter vehicle VIN: ");
                    String saleVin = scanner.nextLine();

                    System.out.print("Enter sale date (YYYY-MM-DD): ");
                    String saleDate = scanner.nextLine();
                 //   Sales sale = new Sales(id, buyerName, saleVin, saleDate);

                 //   salesDao.createSale(sale);
                    System.out.println("Sale added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Sale ID to delete: ");
                    int saleId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    salesDao.deleteSale(saleId);
                    System.out.println("Sale removed successfully.");
                    break;

                case 3:
                    List<Sales> allSales = salesDao.getAllSales();
                    for (Sales sales : allSales) {
                        System.out.println(sales);
                    }
                    break;

                case 4:
                    System.out.print("Enter lessee name: ");
                    String lesseeName = scanner.nextLine();
                    System.out.print("Enter vehicle VIN: ");
                    String leaseVin = scanner.nextLine();

                      Lease lease = new Lease(lesseeName, leaseVin);
                      int id = leaseDao.createLease(lease);
                    System.out.println("Lease added successfully.");
                    break;

                case 5:
                    System.out.print("Enter Lease ID to delete: ");
                    int leaseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    leaseDao.deleteLease(leaseId);
                    System.out.println("Lease removed successfully.");
                    break;

                case 6:
                    List<Lease> leases = leaseDao.allLeases();
                    leases.forEach(System.out::println);
                    System.out.println(leases);
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }
}