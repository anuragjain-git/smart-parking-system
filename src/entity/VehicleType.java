package entity;

public class VehicleType {
    private int typeId;
    private String vehicleType;
    private double baseRatePerHour;

    public VehicleType(int typeId, String vehicleType, double baseRatePerHour) {
        this.typeId = typeId;
        this.vehicleType = vehicleType;
        this.baseRatePerHour = baseRatePerHour;
    }

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getBaseRatePerHour() {
		return baseRatePerHour;
	}

	public void setBaseRatePerHour(double baseRatePerHour) {
		this.baseRatePerHour = baseRatePerHour;
	}

	@Override
	public String toString() {
	    return "VehicleType [id=" + typeId + ", type=" + vehicleType + ", rate/hr=" + baseRatePerHour + "]";
	}

}

