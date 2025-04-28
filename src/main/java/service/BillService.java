package service;

import model.Bill;
import model.ParkingLot;
import model.ParkingSpot;
import model.ParkingTicket;
import model.constant.ParkingSpotStatus;
import repository.*;
import service.billAmountCalculationStrategy.BillAmountCalculationStrategy;
import service.billAmountCalculationStrategy.BillAmountCalculationStrategyFactory;

import java.time.Duration;
import java.time.LocalDateTime;

public class BillService {
    private ParkingTicketRepository parkingTicketRepository;
    private ParkingGateRepository parkingGateRepository;
    private BillRepository billRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingLotRepository parkingLotRepository;

    public BillService(ParkingTicketRepository parkingTicketRepository, ParkingGateRepository parkingGateRepository,
                       BillRepository billRepository, ParkingSpotRepository parkingSpotRepository,
                       ParkingLotRepository parkingLotRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.parkingGateRepository = parkingGateRepository;
        this.billRepository = billRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Bill generateBill(int ticketId, int exitGateId, ParkingLot parkingLot) {
        ParkingTicket parkingTicket = parkingTicketRepository.findByID(ticketId);

        Bill bill = new Bill();
        bill.setExitTime(LocalDateTime.now());
        bill.setExitGate(parkingGateRepository.findByID(exitGateId));
        bill.setParkingTicket(parkingTicket);
        bill.setOperator(parkingGateRepository.findByID(exitGateId).getOperator());
        Duration duration = Duration.between(parkingTicket.getEntryTime(), LocalDateTime.now());
        long durationInSeconds = duration.getSeconds();;

        BillAmountCalculationStrategy billAmountCalculationStrategy =
                BillAmountCalculationStrategyFactory.getBillAmountCalculationStrategy();
        double amount = billAmountCalculationStrategy.generateOverallBill(durationInSeconds,
                parkingTicket.getVehicle().getVehicleType(), parkingTicket.getParkingSpot().getSpotTier());
        bill.setAmount(amount);

        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        parkingSpot.setSpotStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpot = parkingSpotRepository.update(parkingSpot.getId(), parkingSpot);

        parkingLot.setAvailableSlots(parkingLot.getAvailableSlots() + 1);
        parkingLot = parkingLotRepository.update(parkingLot.getId(), parkingLot);

        return billRepository.save(bill);
    }

}
