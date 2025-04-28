package model;

import java.time.LocalDateTime;
import java.util.Date;

public class ParkingTicket {
    private int id;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private ParkingGate parkingGate;
    private Operator operator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "ticket id= " + id +
                ", parkingSpot= " + parkingSpot.getParkingSpotNumber() +
                ", entryTime= " + entryTime +
                ", vehicle= " + vehicle.getVehicleNumber() +
                ", parkingGate= " + parkingGate.getGateNumber() +
                ", operator= " + operator.getName() +
                '}';
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingGate getParkingGate() {
        return parkingGate;
    }

    public void setParkingGate(ParkingGate parkingGate) {
        this.parkingGate = parkingGate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
