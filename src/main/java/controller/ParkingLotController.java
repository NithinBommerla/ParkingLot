package controller;

import model.Bill;
import model.ParkingLot;
import model.ParkingTicket;
import model.Vehicle;
import model.constant.ParkingSpotTier;
import model.constant.VehicleType;
import service.ParkingLotService;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public boolean isSlotAvailable() {
        return true;
    }

    public boolean isSlotAvailable(VehicleType vehicleType) {
        // TODO: Check Availability based on the class or Type of the vehicle.
        return true;
    }

    public ParkingLot initialiseParkingLot(int noOfFloors, int noOfSpotsInAFloor) {
        //TODO: Add validations to limit the no of floors and spots
        // ex: Floors <= 10 && Spots <= 1000
        return parkingLotService.initialiseParkingLot(noOfFloors, noOfSpotsInAFloor);
    }

    public void showParkingLot(ParkingLot parkingLot) {
        System.out.println("Displaying Parking Lot Status");
        parkingLotService.showParkingLot(parkingLot);
    }
}
