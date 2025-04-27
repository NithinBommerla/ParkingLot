package model;

import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotTier;
import model.constant.VehicleType;

public class ParkingSpot {
    private int id;
    private String parkingSpotNumber;
    private VehicleType vehicleTypeSupported; // ParkingSpotType (Same as vehicle Type)
    private ParkingSpotStatus spotStatus;
    private ParkingSpotTier spotTier;
    private Vehicle vehicle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public VehicleType getVehicleTypeSupported() {
        return vehicleTypeSupported;
    }

    public void setVehicleTypeSupported(VehicleType vehicleTypeSupported) {
        this.vehicleTypeSupported = vehicleTypeSupported;
    }

    public ParkingSpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(ParkingSpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public ParkingSpotTier getSpotTier() {
        return spotTier;
    }

    public void setSpotTier(ParkingSpotTier spotTier) {
        this.spotTier = spotTier;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
