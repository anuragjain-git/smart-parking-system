package entity;

public class Vehicle {
    private int vehicleId;
    private int userId;
    private int typeId;
    private String licensePlate;
    private String make;
    private String model;
    private String color;

    public Vehicle(int vehicleId, int userId, int typeId, String licensePlate, String make, String model, String color) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.typeId = typeId;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.color = color;
    }

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
	    return "Vehicle [id=" + vehicleId + ", userId=" + userId + ", typeId=" + typeId +
	           ", plate=" + licensePlate + ", make=" + make + ", model=" + model + ", color=" + color + "]";
	}

}
