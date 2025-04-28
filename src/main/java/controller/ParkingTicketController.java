package controller;

import model.ParkingLot;
import model.ParkingTicket;
import model.Vehicle;
import model.constant.ParkingSpotTier;
import service.ParkingTicketService;

public class ParkingTicketController {

    private ParkingTicketService parkingTicketService;

    public ParkingTicketController(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    public ParkingTicket generateTicket(ParkingLot parkingLot, String vehicleNumber, ParkingSpotTier parkingSpotTier, int entryGateId) {
        return parkingTicketService.generateTicket(parkingLot, vehicleNumber, parkingSpotTier, entryGateId);
    }

    public void displayTicketDetails(ParkingTicket parkingTicket) {
        System.out.println("Displaying Parking Ticket Details");
        System.out.println(parkingTicket.toString());
    }
}
