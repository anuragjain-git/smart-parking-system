package day4;

import java.util.Scanner;
import java.util.ArrayList;

public class PolymorphismExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";
        ArrayList<AbstractFeeCalculator> sessionHistory = new ArrayList<>();

        do {
            System.out.println("\nSmart Parking System (Using Abstract Class + Polymorphic Copy)");
            System.out.println("Select vehicle type:");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Truck");
            System.out.print("Enter choice (1-3): ");
            int vehicleOption = sc.nextInt();

            System.out.print("Enter parking duration in hours (max 24): ");
            int hours = sc.nextInt();

            if (hours > 24 || hours <= 0) {
                System.out.println("Invalid duration.");
                continue;
            }

            System.out.print("Enter entry hour (0-23): ");
            int entryHour = sc.nextInt();

            if (entryHour < 0 || entryHour > 23) {
                System.out.println("Invalid entry hour.");
                continue;
            }

            System.out.print("Is the user a VIP? (yes/no): ");
            boolean isVIP = sc.next().equalsIgnoreCase("yes");

            AbstractFeeCalculator calculator;
            AbstractFeeCalculator copyBike;

            switch (vehicleOption) {
                case 1: 
                	calculator = new CarCalculator(hours, entryHour, isVIP); 
                	break;
                case 2: 
                	calculator = new BikeCalculator(hours, entryHour, isVIP); 
                	copyBike = calculator.copy();
                	copyBike.setVIP(false);
                	sessionHistory.add(copyBike);
                	break;
                case 3: calculator = new TruckCalculator(hours, entryHour, isVIP); break;
                default:
                    System.out.println("Invalid vehicle type.");
                    continue;
            }

            System.out.printf("Total parking fee: ₹%.2f\n", calculator.calculateTotalFee());
            System.out.println("Peak hour extra charges (25%): ₹" + calculator.calculateExtraCharges());
            System.out.println("VIP discount (20%): ₹" + calculator.calculateDiscount());

            System.out.print("Process another vehicle? (yes/no): ");
            choice = sc.next();
        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("\nThank you for using Smart Parking System.");

        System.out.print("Do you want to print session history (yes/no)? ");
        boolean printHistory = sc.next().equalsIgnoreCase("yes");

        if (printHistory) {
            System.out.println("\n--- Parking Session History ---");
            for (AbstractFeeCalculator session : sessionHistory) {
                System.out.println("Vehicle: " + session.vehicleName);
                System.out.println("Hours: " + session.hours + ", Entry: " + session.entryHour + ", VIP: " + session.isVIP);
                System.out.printf("Fee: ₹%.2f, Extra: ₹%.2f, Discount: ₹%.2f\n\n",
                        session.calculateTotalFee(),
                        session.calculateExtraCharges(),
                        session.calculateDiscount());
            }
        }
    }
}
