package day4;

import java.util.*;

public class CopyConstructorExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";
        
        ArrayList<AbstractFeeCalculator> sessionHistory = new ArrayList<>();

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
//            AbstractFeeCalculator copy;

            switch (vehicleOption) {
	            case 1:
	                calculator = new CarCalculator(hours, entryHour, isVIP);
//	                copy = calculator; // same reference
	                CarCalculator copiedCar = new CarCalculator((CarCalculator) calculator);
//	                copiedCar.setHours(hours);           
//	                copiedCar.setEntryHour(entryHour);   
	                copiedCar.setVIP(false);
	                sessionHistory.add(copiedCar);
	                break;

	            case 2:
	                calculator = new BikeCalculator(hours, entryHour, isVIP);
	                BikeCalculator copiedBike = new BikeCalculator((BikeCalculator) calculator);
//	                copiedBike.setHours(hours);          
//	                copiedBike.setEntryHour(entryHour);  
//	                copiedBike.setVIP(isVIP);            
	                sessionHistory.add(copiedBike);
	                break;
	
	            case 3:
	                calculator = new TruckCalculator(hours, entryHour, isVIP);
	                TruckCalculator copiedTruck = new TruckCalculator((TruckCalculator) calculator);
//	                copiedTruck.setHours(hours);         
//	                copiedTruck.setEntryHour(entryHour); 
//	                copiedTruck.setVIP(isVIP);           
	                sessionHistory.add(copiedTruck);
	                break;
	
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

        System.out.println("\nThank you for using Smart Parking System.\n");
        
        System.out.print("Do you want to print session history(Yes/No): ");
        boolean printHistory = sc.next().equalsIgnoreCase("yes");
        
        if(printHistory) {
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
