package entity;

import java.time.LocalDateTime;

public class ParkingTransaction {
    private int transactionId;
    private int vehicleId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double calculatedExtraCharges;
    private double calculatedDiscount;
    private double calculatedFee;
    private boolean peakHourChargeApplied;
    private double durationHours;
    private String status;

    public ParkingTransaction(int transactionId, int vehicleId, LocalDateTime entryTime, LocalDateTime exitTime,
                              double calculatedExtraCharges, double calculatedDiscount, double calculatedFee,
                              boolean peakHourChargeApplied, double durationHours, String status) {
        this.transactionId = transactionId;
        this.vehicleId = vehicleId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.calculatedExtraCharges = calculatedExtraCharges;
        this.calculatedDiscount = calculatedDiscount;
        this.calculatedFee = calculatedFee;
        this.peakHourChargeApplied = peakHourChargeApplied;
        this.durationHours = durationHours;
        this.status = status;
    }

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public double getCalculatedExtraCharges() {
		return calculatedExtraCharges;
	}

	public void setCalculatedExtraCharges(double calculatedExtraCharges) {
		this.calculatedExtraCharges = calculatedExtraCharges;
	}

	public double getCalculatedDiscount() {
		return calculatedDiscount;
	}

	public void setCalculatedDiscount(double calculatedDiscount) {
		this.calculatedDiscount = calculatedDiscount;
	}

	public double getCalculatedFee() {
		return calculatedFee;
	}

	public void setCalculatedFee(double calculatedFee) {
		this.calculatedFee = calculatedFee;
	}

	public boolean isPeakHourChargeApplied() {
		return peakHourChargeApplied;
	}

	public void setPeakHourChargeApplied(boolean peakHourChargeApplied) {
		this.peakHourChargeApplied = peakHourChargeApplied;
	}

	public double getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(double durationHours) {
		this.durationHours = durationHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
	    return "Transaction [id=" + transactionId + ", vehicleId=" + vehicleId +
	           ", entry=" + entryTime + ", exit=" + exitTime +
	           ", extraCharges=" + calculatedExtraCharges +
	           ", discount=" + calculatedDiscount +
	           ", fee=" + calculatedFee +
	           ", peakHour=" + peakHourChargeApplied +
	           ", duration=" + durationHours +
	           ", status=" + status + "]";
	}
}
