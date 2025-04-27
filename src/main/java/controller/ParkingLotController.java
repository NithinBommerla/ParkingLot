package controller;

import model.Bill;
import model.ParkingTicket;
import model.Vehicle;
import model.constant.ParkingSpotTier;
import model.constant.VehicleType;

public class ParkingLotController {

    public boolean isSlotAvailable() {
        return true;
    }

    public boolean isSlotAvailable(VehicleType vehicleType) {
        // TODO: Check Availability based on the class or Type of the vehicle.
        return true;
    }

    public ParkingTicket generateTicket(String vehicleNumber, ParkingSpotTier spotTier, int entryGateId) {
        // If Vehicle details are not present
        // call generate ticket with vehicle object and return the ticket
        // i.e. call generateTicket(Vehicle vehicle, ......)
        return null;
    }

    public ParkingTicket generateTicket(Vehicle vehicle, ParkingSpotTier spotTier, int entryGateId) {
        return null;
    }

    public Bill generateBill(int ticketId, int exitGateId) {
        return null;
    }

    public Bill billPayment(Bill bill) {
        return null;
    }

    public void displayParkingLotStatus() {
        System.out.println("Displaying Parking Lot Status");
    }
}
