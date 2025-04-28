package service;

import exception.VehicleNotFoundException;
import model.*;
import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotTier;
import model.constant.VehicleType;
import repository.*;
import service.parkingSpotAssignmentStrategy.ParkingSpotAssignmentStrategy;
import service.parkingSpotAssignmentStrategy.ParkingSpotAssignmentStrategyFactory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ParkingTicketService {

    private ParkingGateRepository parkingGateRepository;
    private ParkingTicketRepository parkingTicketRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingLotRepository parkingLotRepository;
    private VehicleRepository vehicleRepository;

    public ParkingTicketService(ParkingLotRepository parkingLotRepository, ParkingGateRepository parkingGateRepository,
                                ParkingTicketRepository parkingTicketRepository, ParkingSpotRepository parkingSpotRepository,
                                VehicleRepository vehicleRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingGateRepository = parkingGateRepository;
        this.parkingTicketRepository = parkingTicketRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository =  vehicleRepository;

    }

    public ParkingTicket generateTicket(ParkingLot parkingLot, String vehicleNumber,
                                        ParkingSpotTier parkingSpotTier, int entryGateId) {
        // If Vehicle details are not present
        // call generate ticket with vehicle object and return the ticket
        // i.e. call generateTicket(Vehicle vehicle, ......)
        Vehicle vehicle = null;
        try {
            vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        } catch (VehicleNotFoundException ve) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Vehicle doesn't exist with this Number, Please Enter vehicle details");
            System.out.println("Please Enter Vehicle Type\n1.Heavy Vehicle\n2.TWO Wheeler\n3.THREE Wheeler\n4.FOUR Wheeler\n");
            int vehicleType = sc.nextInt();
            System.out.println("Please Enter VehicleInfo");
            String vehicleInfo = sc.next();

            vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleInfo(vehicleInfo);
            switch (vehicleType){
                case 1: vehicle.setVehicleType(VehicleType.HEAVY_VEHICLE);
                    break;
                case 2: vehicle.setVehicleType(VehicleType.TWO_WHEELER);
                    break;
                case 3: vehicle.setVehicleType(VehicleType.THREE_WHEELER);
                    break;
                case 4: vehicle.setVehicleType(VehicleType.FOUR_WHEELER);
                    break;
                default: System.out.println("Invalid input for vehicle type");
                    break;
            }
            vehicle = vehicleRepository.save(vehicle);
        }
        return generateTicket(parkingLot, vehicle, parkingSpotTier, entryGateId);
    }

    public ParkingTicket generateTicket(ParkingLot parkingLot, Vehicle vehicle,
                                        ParkingSpotTier parkingSpotTier, int entryGateId) {
        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy = ParkingSpotAssignmentStrategyFactory.getParkingSpotAssignmentStrategy();
        ParkingSpot parkingSpot = parkingSpotAssignmentStrategy.findParkingSpot(parkingLot, vehicle, parkingSpotTier);

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setEntryTime(LocalDateTime.now());
        parkingTicket.setParkingGate(parkingGateRepository.findByID(entryGateId));
        parkingTicket.setParkingSpot(parkingSpot);
        parkingTicket.setVehicle(vehicle);
        parkingTicket.setOperator(parkingGateRepository.findByID(entryGateId).getOperator());

        parkingTicket = parkingTicketRepository.save(parkingTicket);

        parkingSpot.setSpotStatus(ParkingSpotStatus.OCCUPIED);
        parkingSpot = parkingSpotRepository.update(parkingSpot.getId(), parkingSpot);

        parkingLot.setAvailableSlots(parkingLot.getAvailableSlots() - 1);
        parkingLot = parkingLotRepository.update(parkingLot.getId(), parkingLot);

        return parkingTicket;
    }

}
