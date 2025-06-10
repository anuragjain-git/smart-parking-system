package day3;

abstract class AbstractFeeCalculator {
    protected int hours;
    protected int entryHour;
    protected boolean isVIP;
    final String vehicleName;

    public AbstractFeeCalculator(int hours, int entryHour, boolean isVIP, String vehicleName) {
        this.hours = hours;
        this.entryHour = entryHour;
        this.isVIP = isVIP;
        this.vehicleName = vehicleName;
    }
    
    public AbstractFeeCalculator(AbstractFeeCalculator others) {
        this.hours = others.hours;
        this.entryHour = others.entryHour;
        this.isVIP = others.isVIP;
        this.vehicleName = others.vehicleName;
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
        super(hours, entryHour, isVIP, "car");
    }

    public CarCalculator(CarCalculator other) {
		super(other);
	}

	protected double getRatePerHour() {
        return 20;
    }
}

class BikeCalculator extends AbstractFeeCalculator {
    public BikeCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP, "Bike");
    }
    
    public BikeCalculator(BikeCalculator other) {
		super(other);
	}

    protected double getRatePerHour() {
        return 10;
    }
}

class TruckCalculator extends AbstractFeeCalculator {
    public TruckCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP, "Truck");
    }
    
    public TruckCalculator(TruckCalculator other) {
        super(other);
    }

    protected double getRatePerHour() {
        return 30;
    }
}