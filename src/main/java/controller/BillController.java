package controller;

import model.Bill;
import model.ParkingLot;
import service.BillService;

public class BillController {

    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public Bill generateBill(int ticketId, int exitGateId, ParkingLot parkingLot) {
        return billService.generateBill(ticketId, exitGateId, parkingLot);
    }

    public Bill billPayment(Bill bill) {
        return null;
    }

    public void displayBillDetails(Bill bill) {
        System.out.println("Displaying Bill Details");
        System.out.println(bill.toString());
    }
}
