package day2;

import java.util.Scanner;

@FunctionalInterface
interface Vehicle1 {
    double getRatePerHour();
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";

        do {
            System.out.println("\nParking Fee System (Using Interface)");

            System.out.println("Select vehicle type:");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Truck");
            System.out.print("Enter choice (1-3): ");
            int vehicleOption = sc.nextInt();

            System.out.print("Enter parking duration in hours (max 24): ");
            int hours = sc.nextInt();

            if (hours > 24 || hours <= 0) {
                System.out.println("Parking duration must be between 1 and 24 hours. Entry denied.");
                continue;
            }

            System.out.print("Enter entry hour (0-23): ");
            int entryHour = sc.nextInt();

            if (entryHour < 0 || entryHour > 23) {
                System.out.println("Invalid entry hour. Entry denied.");
                continue;
            }

            System.out.print("Is the user a VIP? (yes/no): ");
            boolean isVIP = sc.next().equalsIgnoreCase("yes");

            Vehicle vehicle;
            
            // example of lambda 
            // (name) -> {System.out.println("Hello " + name + " Welcome");};

            switch (vehicleOption) {
                case 1:
                    vehicle = () -> 20;
                    break;
                case 2:
                	vehicle = () -> 10;
                    break;
                case 3:
                	vehicle = () -> 30;
                    break;
                default:
                    System.out.println("Invalid vehicle type selected.");
                    continue;
            }
            
            double totalFee = vehicle.getRatePerHour() * hours;
            double extracharges = 0;
            double discount = 0;

            if (entryHour >= 8 && entryHour < 20) {
                extracharges = totalFee * 0.25;
            }

            if (isVIP) {
                discount = (totalFee + totalFee) * 0.20;
            }
            
            totalFee += extracharges - discount;

            System.out.println("Total parking fee: ₹" + totalFee);
            
            if (extracharges > 0) {
                System.out.println("Peak hour extra charges (25%): Rs " + extracharges);
            }
            
            if (discount > 0) {
                System.out.println("VIP discount (20%): ₹" + discount);
            }

            System.out.print("Do you want to process another vehicle? (yes/no): ");
            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using the Smart Parking System.");
        sc.close();
    }
}