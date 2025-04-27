package model;

import java.util.List;

public class ParkingLot {
    private int Id;
    private String name; // Name of Parking Lot
    private String address; // Address where Parking Lot is located
    private List<ParkingFloor> parkingFloorList;
    private List<ParkingGate> parkingGateList;
    private int totalCapacity;
    private int availableSlots;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ParkingFloor> getParkingFloorList() {
        return parkingFloorList;
    }

    public void setParkingFloorList(List<ParkingFloor> parkingFloorList) {
        this.parkingFloorList = parkingFloorList;
    }

    public List<ParkingGate> getParkingGateList() {
        return parkingGateList;
    }

    public void setParkingGateList(List<ParkingGate> parkingGateList) {
        this.parkingGateList = parkingGateList;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
