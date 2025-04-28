import controller.BillController;
import controller.ParkingLotController;
import controller.ParkingTicketController;
import exception.ParkingLotNotFoundException;
import model.Bill;
import model.ParkingGate;
import model.ParkingLot;
import model.ParkingTicket;
import model.constant.ParkingSpotTier;
import repository.*;
import service.BillService;
import service.ParkingLotService;
import service.ParkingTicketService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BillRepository billRepository = new BillRepository();
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
        ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();
        ParkingGateRepository parkingGateRepository = new ParkingGateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository,
                parkingFloorRepository, parkingSpotRepository, parkingGateRepository);
        ParkingTicketService parkingTicketService = new ParkingTicketService(parkingLotRepository,
                parkingGateRepository, parkingTicketRepository, parkingSpotRepository, vehicleRepository);
        BillService billService = new BillService(parkingTicketRepository, parkingGateRepository,
                billRepository, parkingSpotRepository, parkingLotRepository);

        ParkingLotController parkingLotController = new ParkingLotController(parkingLotService);
        ParkingTicketController parkingTicketController = new ParkingTicketController(parkingTicketService);
        BillController billController = new BillController(billService);

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Parking Lot System");
        System.out.println("Enter Number of Floors In the parking Lot:");
        int noOfFloors = sc.nextInt();
        System.out.println("Enter Number of Spots in a Floor:");
        int noOfSpots = sc.nextInt();
        ParkingLot parkingLot = parkingLotController.initialiseParkingLot(noOfFloors,noOfSpots);
        System.out.println("Creating Parking Lot ....");
        parkingLotController.showParkingLot(parkingLot);
        System.out.println("Parking Lot is now ready to use!!");

        while(true) {
            System.out.println("Choose one of the following");
            System.out.println("1. Enter a new vehicle\n2. Exit vehicle");
            int option = sc.nextInt();
            if(option == 1) {
                if(parkingLotController.isSlotAvailable(parkingLot)){
                    System.out.println("Please Enter vehicle Number");
                    String vehicleNumber = sc.next();
                    ParkingTicket parkingTicket = parkingTicketController.generateTicket(parkingLot,
                            vehicleNumber, ParkingSpotTier.NORMAL, 1);
                    parkingTicketController.displayTicketDetails(parkingTicket);
                    parkingLotController.showParkingLot(parkingLot);

                } else {
                    System.out.println("Parking Lot is full, Sorry for the Inconvenience caused");
                }
            } else {
                System.out.println("Please Enter your Parking Ticket Id");
                int ticketId = sc.nextInt();
                Bill bill = billController.generateBill(ticketId, 2, parkingLot);
                billController.displayBillDetails(bill);
                System.out.println("Please choose Payment Mode\n1.Cash\n2.Card\n3.Online(UPI)");
                int paymentMode = sc.nextInt();
                // Generate Bill with payment Mode
                // Display Bill
                parkingLotController.showParkingLot(parkingLot);
            }
        }
    }
}
