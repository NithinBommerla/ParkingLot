import controller.BillController;
import controller.ParkingLotController;
import controller.ParkingTicketController;
import model.Bill;
import model.ParkingTicket;
import model.constant.ParkingSpotTier;
import repository.*;

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

        ParkingLotController parkingLotController = new ParkingLotController();
        ParkingTicketController parkingTicketController = new ParkingTicketController();
        BillController billController = new BillController();

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Parking Lot System");
        while(true) {
            System.out.println("Choose one of the following");
            System.out.println("1. Enter a new vehicle\n2. Exit vehicle");
            int option = sc.nextInt();
            if(option == 1) {
                if(parkingLotController.isSlotAvailable()){
                    System.out.println("Please Enter vehicle Number");
                    String vehicleNumber = sc.next();
                    ParkingTicket parkingTicket = parkingLotController.generateTicket(vehicleNumber, ParkingSpotTier.NORMAL, 1);
                    parkingTicketController.displayTicketDetails(parkingTicket);
                    parkingLotController.displayParkingLotStatus();

                } else {
                    System.out.println("Parking Lot is full, Sorry for the Inconvenience caused");
                }
            } else {
                System.out.println("Please Enter your Parking Ticket Id");
                int ticketId = sc.nextInt();
                Bill bill = parkingLotController.generateBill(ticketId, 2);
                billController.displayBillDetails(bill);
                System.out.println("Please choose Payment Mode\n1. Cash\n2. Card\n3. Online(UPI)");
                int paymentMode = sc.nextInt();
                // Generate Bill with payment Mode
                // Display Bill
                parkingLotController.displayParkingLotStatus();
            }
        }
    }
}
