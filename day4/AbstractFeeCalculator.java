package day4;

abstract class AbstractFeeCalculator {
    protected int hours;
    protected int entryHour;
    protected boolean isVIP;
    protected String vehicleName;

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
    
    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setEntryHour(int entryHour) {
        this.entryHour = entryHour;
    }

    public void setVIP(boolean isVIP) {
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

    public abstract AbstractFeeCalculator copy();
}

class CarCalculator extends AbstractFeeCalculator {
    public CarCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP, "Car");
    }

    public CarCalculator(CarCalculator other) {
        super(other);
    }

    @Override
    protected double getRatePerHour() {
        return 20;
    }

    @Override
    public AbstractFeeCalculator copy() {
        return new CarCalculator(this);
    }
}

class BikeCalculator extends AbstractFeeCalculator {
    public BikeCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP, "Bike");
    }

    public BikeCalculator(BikeCalculator other) {
        super(other);
    }

    @Override
    protected double getRatePerHour() {
        return 10;
    }

    @Override
    public AbstractFeeCalculator copy() {
        return new BikeCalculator(this);
    }
}

class TruckCalculator extends AbstractFeeCalculator {
    public TruckCalculator(int hours, int entryHour, boolean isVIP) {
        super(hours, entryHour, isVIP, "Truck");
    }

    public TruckCalculator(TruckCalculator other) {
        super(other);
    }

    @Override
    protected double getRatePerHour() {
        return 30;
    }

    @Override
    public AbstractFeeCalculator copy() {
        return new TruckCalculator(this);
    }
}