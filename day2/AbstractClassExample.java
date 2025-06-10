package day2;

import java.util.Scanner;

abstract class AbstractFeeCalculator {
    protected int hours;
    protected int entryHour;
    protected boolean isVIP;

    public AbstractFeeCalculator(int hours, int entryHour, boolean isVIP) {
        this.hours = hours;
        this.entryHour = entryHour;
        this.isVIP = isVIP;
    }
    
    public double calculateBaseFee() {
    	return getRatePerHour() * hours;
    }
    
    public double calculateExtraCharges() {               
        if (entryHour >= 8 && entryHour < 20) {
            return calculateBaseFee() * 0.25;
        }
    	return 0;
    }
    
    public double calculateDiscount() {
    	if (isVIP) {
            return (calculateBaseFee() + calculateExtraCharges()) * 0.20;
        }
    	return 0;
    }

    public double calculateTotalFee() {
        return calculateBaseFee() + calculateExtraCharges() - calculateDiscount();
    }

    protected abstract double getRatePerHour();
}

class CarCalculator extends AbstractFeeCalculator {
    public CarCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP);
    }

    protected double getRatePerHour() {
        return 20;
    }
}

class BikeCalculator extends AbstractFeeCalculator {
    public BikeCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP);
    }

    protected double getRatePerHour() {
        return 10;
    }
}

class TruckCalculator extends AbstractFeeCalculator {
    public TruckCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP);
    }

    protected double getRatePerHour() {
        return 30;
    }
}

public class AbstractClassExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";

        do {
            System.out.println("\nSmart Parking System (Using Abstract Class)");
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

            switch (vehicleOption) {
                case 1: calculator = new CarCalculator(hours, entryHour, isVIP); break;
                case 2: calculator = new BikeCalculator(hours, entryHour, isVIP); break;
                case 3: calculator = new TruckCalculator(hours, entryHour, isVIP); break;
                default:
                    System.out.println("Invalid vehicle type.");
                    continue;
            }

            double fee = calculator.calculateTotalFee();
            System.out.printf("Total parking fee: ₹%.2f\n", fee);
            
            double extracharges = calculator.calculateExtraCharges();
            System.out.println("Peak hour extra charges (25%): Rs " + extracharges);
            
            double discount = calculator.calculateDiscount();
            System.out.println("VIP discount (20%): ₹" + discount);
            
            System.out.print("Process another vehicle? (yes/no): ");
            choice = sc.next();
        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using Smart Parking System.");
    }
}
