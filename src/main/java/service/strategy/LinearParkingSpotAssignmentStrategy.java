package service.strategy;

import exception.ParkingSpotNotAvailableException;
import exception.ParkingSpotNotFoundException;
import model.ParkingFloor;
import model.ParkingLot;
import model.ParkingSpot;
import model.Vehicle;
import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotTier;

public class LinearParkingSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy{
    @Override
    public ParkingSpot findParkingSpot(ParkingLot parkingLot, Vehicle vehicle, ParkingSpotTier parkingSpotTier) {
        for(ParkingFloor parkingFloor: parkingLot.getParkingFloorList()) {
            for(ParkingSpot parkingSpot: parkingFloor.getParkingSpotList()) {
                if(parkingSpot.getSpotStatus().equals(ParkingSpotStatus.AVAILABLE)
                && parkingSpot.getVehicleTypeSupported().equals(vehicle.getVehicleType())
                && parkingSpot.getSpotTier().equals(parkingSpotTier)) {
                    return parkingSpot;
                }
            }
        }
        throw new ParkingSpotNotAvailableException("Parking Spot Not Found");
    }
}
