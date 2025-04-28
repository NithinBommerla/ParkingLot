package service;

import model.*;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotTier;
import model.constant.VehicleType;
import repository.ParkingFloorRepository;
import repository.ParkingGateRepository;
import repository.ParkingLotRepository;
import repository.ParkingSpotRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingGateRepository parkingGateRepository;

    // Constructor
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingFloorRepository parkingFloorRepository, ParkingSpotRepository parkingSpotRepository, ParkingGateRepository parkingGateRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingGateRepository = parkingGateRepository;
    }

    public ParkingLot initialiseParkingLot(int noOfFloors, int noOfSpotsInAFloor) {
        Operator operator1 = new Operator();
        operator1.setId(1);
        operator1.setName("Operator 1");
        operator1.setEmail("operator1@parkinglot.com");

        Operator operator2 = new Operator();
        operator2.setId(2);
        operator2.setName("Operator 2");
        operator2.setEmail("operator2@parkinglot.com");

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Parking Lot 1");
        parkingLot.setAddress("Street A, Place B, City C, 123456");

        ParkingGate entryGate = new ParkingGate();
        entryGate.setGateNumber("Gate 01");
        entryGate.setGateType(ParkingGateType.ENTRY);
        entryGate.setOperator(operator1);

        ParkingGate exitGate = new ParkingGate();
        exitGate.setGateNumber("Gate 02");
        exitGate.setGateType(ParkingGateType.EXIT);
        exitGate.setOperator(operator2);

        entryGate = parkingGateRepository.save(entryGate);
        exitGate = parkingGateRepository.save(exitGate);

        parkingLot.setParkingGateList(List.of(entryGate, exitGate));

        List<ParkingFloor> parkingFloors = new ArrayList<>();

        for(int i = 1; i <= noOfFloors; i++) {
            ParkingFloor parkingFloor = new ParkingFloor();
            parkingFloor.setFloorNumber("Floor"+i);
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for(int j = 1; j <= noOfSpotsInAFloor; j++) {
                ParkingSpot parkingSpot = new ParkingSpot();
                // TODO: Use Builder Pattern to Create ParkingSpot Object
                parkingSpot.setParkingSpotNumber(i+"0"+j);
                parkingSpot.setVehicleTypeSupported(VehicleType.FOUR_WHEELER);
                parkingSpot.setSpotStatus(ParkingSpotStatus.AVAILABLE);
                parkingSpot.setSpotTier(ParkingSpotTier.NORMAL);
                parkingSpot = parkingSpotRepository.save(parkingSpot);
                parkingSpots.add(parkingSpot);
            }
            parkingFloor.setParkingSpotList(parkingSpots);
            parkingFloor = parkingFloorRepository.save(parkingFloor);
            parkingFloors.add(parkingFloor);
        }

        parkingLot.setParkingFloorList(parkingFloors);
        int totalCapacity = noOfFloors * noOfSpotsInAFloor;
        parkingLot.setTotalCapacity(totalCapacity);
        parkingLot.setAvailableSlots(totalCapacity);
        parkingLot = parkingLotRepository.save(parkingLot);

        return parkingLot;
    }

    public void showParkingLot(ParkingLot parkingLot) {
        System.out.println("Parking Lot: "+parkingLot.getName());
        System.out.println("---------------------------------------------");
        for(int i = 0; i < parkingLot.getParkingFloorList().size(); i++) {
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
            System.out.println("Parking Floor: "+parkingFloor.getFloorNumber());
            System.out.println("---------------------------------------------");
            for(ParkingSpot parkingSpot : parkingFloor.getParkingSpotList()) {
                System.out.print("|");
                if(parkingSpot.getSpotStatus().equals(ParkingSpotStatus.OCCUPIED)) System.out.print("X");
                else if (parkingSpot.getSpotStatus().equals(ParkingSpotStatus.AVAILABLE)) System.out.print(" ");
                else System.out.print("-");
                System.out.print("|");
            }
            System.out.println("\n---------------------------------------------");
        }
        System.out.println("Available Slots: " +parkingLot.getAvailableSlots());
    }
}
