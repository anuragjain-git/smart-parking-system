package app;

import dao.*;
import entity.*;

import java.util.Scanner;
import java.time.LocalDateTime;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserDao userDao = new UserDao();
        VehicleTypeDao typeDao = new VehicleTypeDao();
        VehicleDao vehicleDao = new VehicleDao();
        ParkingTransactionDao txDao = new ParkingTransactionDao();

        int choice;
        do {
            System.out.println("\n===== Parking Management System =====");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Vehicle Types");
            System.out.println("3. Manage Vehicles");
            System.out.println("4. Manage Parking Transactions");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> manageUsers(sc, userDao);
                case 2 -> manageVehicleTypes(sc, typeDao);
                case 3 -> manageVehicles(sc, vehicleDao);
                case 4 -> manageTransactions(sc, txDao);
            }

        } while (choice != 0);
        System.out.println("Goodbye!");
    }

    static void manageUsers(Scanner sc, UserDao dao) {
        System.out.println("\n--- User Management ---");
        System.out.println("1. Add");
        System.out.println("2. Get by ID");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1 -> {
                System.out.print("Username: ");
                String uname = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("VIP (true/false): ");
                boolean vip = Boolean.parseBoolean(sc.nextLine());
                dao.insertUser(new User(0, uname, email, vip));
            }
            case 2 -> {
                System.out.print("User ID: ");
                int id = sc.nextInt();
                System.out.println(dao.getUserById(id));
            }
            case 3 -> {
                System.out.print("User ID to update: ");
                int id = sc.nextInt(); sc.nextLine();
                User u = dao.getUserById(id);
                if (u == null) return;
                System.out.print("New username: "); u.setUsername(sc.nextLine());
                System.out.print("New email: "); u.setEmail(sc.nextLine());
                System.out.print("Is VIP: "); u.setVip(Boolean.parseBoolean(sc.nextLine()));
                dao.updateUser(u);
            }
            case 4 -> {
                System.out.print("User ID: ");
                dao.deleteUserById(sc.nextInt());
            }
        }
    }

    static void manageVehicleTypes(Scanner sc, VehicleTypeDao dao) {
        System.out.println("\n--- Vehicle Type Management ---");
        System.out.println("1. Add\n2. Get by ID\n3. Update\n4. Delete");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1 -> {
                System.out.print("Type: "); String type = sc.nextLine();
                System.out.print("Rate/hr: "); double rate = sc.nextDouble();
                dao.insertVehicleType(new VehicleType(0, type, rate));
            }
            case 2 -> {
                System.out.print("Type ID: "); int id = sc.nextInt();
                System.out.println(dao.getVehicleTypeById(id));
            }
            case 3 -> {
                System.out.print("Type ID to update: "); int id = sc.nextInt(); sc.nextLine();
                VehicleType vt = dao.getVehicleTypeById(id);
                if (vt == null) return;
                System.out.print("New type: "); vt.setVehicleType(sc.nextLine());
                System.out.print("New rate/hr: "); vt.setBaseRatePerHour(sc.nextDouble());
                dao.updateVehicleType(vt);
            }
            case 4 -> {
                System.out.print("Type ID: "); dao.deleteVehicleTypeById(sc.nextInt());
            }
        }
    }

    static void manageVehicles(Scanner sc, VehicleDao dao) {
        System.out.println("\n--- Vehicle Management ---");
        System.out.println("1. Add\n2. Get by ID\n3. Update\n4. Delete");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1 -> {
                System.out.print("User ID: "); int userId = sc.nextInt();
                System.out.print("Type ID: "); int typeId = sc.nextInt(); sc.nextLine();
                System.out.print("Plate: "); String plate = sc.nextLine();
                System.out.print("Make: "); String make = sc.nextLine();
                System.out.print("Model: "); String model = sc.nextLine();
                System.out.print("Color: "); String color = sc.nextLine();
                dao.insertVehicle(new Vehicle(0, userId, typeId, plate, make, model, color));
            }
            case 2 -> {
                System.out.print("Vehicle ID: ");
                System.out.println(dao.getVehicleById(sc.nextInt()));
            }
            case 3 -> {
                System.out.print("Vehicle ID to update: "); int id = sc.nextInt(); sc.nextLine();
                Vehicle v = dao.getVehicleById(id);
                if (v == null) return;
                System.out.print("New userId: "); v.setUserId(sc.nextInt());
                System.out.print("New typeId: "); v.setTypeId(sc.nextInt()); sc.nextLine();
                System.out.print("New plate: "); v.setLicensePlate(sc.nextLine());
                System.out.print("Make: "); v.setMake(sc.nextLine());
                System.out.print("Model: "); v.setModel(sc.nextLine());
                System.out.print("Color: "); v.setColor(sc.nextLine());
                dao.updateVehicle(v);
            }
            case 4 -> {
                System.out.print("Vehicle ID: "); dao.deleteVehicleById(sc.nextInt());
            }
        }
    }

    static void manageTransactions(Scanner sc, ParkingTransactionDao dao) {
        System.out.println("\n--- Parking Transactions ---");
        System.out.println("1. Add\n2. Get by ID\n3. Update\n4. Delete");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1 -> {
                System.out.print("Vehicle ID: "); int vid = sc.nextInt(); sc.nextLine();
                System.out.print("Entry time (yyyy-MM-ddTHH:mm): "); LocalDateTime in = LocalDateTime.parse(sc.nextLine());
                System.out.print("Exit time (yyyy-MM-ddTHH:mm): "); LocalDateTime out = LocalDateTime.parse(sc.nextLine());
                System.out.print("Extra Charges: "); double extra = sc.nextDouble();
                System.out.print("Discount: "); double disc = sc.nextDouble();
                System.out.print("Total Fee: "); double fee = sc.nextDouble();
                System.out.print("Peak Hour? true/false: "); boolean peak = sc.nextBoolean();
                System.out.print("Duration Hours: "); double hrs = sc.nextDouble(); sc.nextLine();
                System.out.print("Status: "); String status = sc.nextLine();
                dao.insertTransaction(new ParkingTransaction(0, vid, in, out, extra, disc, fee, peak, hrs, status));
            }
            case 2 -> {
                System.out.print("Transaction ID: ");
                System.out.println(dao.getTransactionById(sc.nextInt()));
            }
            case 3 -> {
                System.out.print("Transaction ID: "); int id = sc.nextInt(); sc.nextLine();
                ParkingTransaction tx = dao.getTransactionById(id);
                if (tx == null) return;
                System.out.print("New vehicleId: "); tx.setVehicleId(sc.nextInt()); sc.nextLine();
                System.out.print("New entry (yyyy-MM-ddTHH:mm): "); tx.setEntryTime(LocalDateTime.parse(sc.nextLine()));
                System.out.print("New exit (yyyy-MM-ddTHH:mm): "); tx.setExitTime(LocalDateTime.parse(sc.nextLine()));
                System.out.print("Extra: "); tx.setCalculatedExtraCharges(sc.nextDouble());
                System.out.print("Discount: "); tx.setCalculatedDiscount(sc.nextDouble());
                System.out.print("Fee: "); tx.setCalculatedFee(sc.nextDouble());
                System.out.print("Peak? "); tx.setPeakHourChargeApplied(sc.nextBoolean());
                System.out.print("Duration: "); tx.setDurationHours(sc.nextDouble()); sc.nextLine();
                System.out.print("Status: "); tx.setStatus(sc.nextLine());
                dao.updateTransaction(tx);
            }
            case 4 -> {
                System.out.print("Transaction ID: "); dao.deleteTransactionById(sc.nextInt());
            }
        }
    }
}