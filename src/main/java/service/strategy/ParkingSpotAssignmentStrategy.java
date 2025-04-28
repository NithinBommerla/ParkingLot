package service.strategy;

import model.ParkingLot;
import model.ParkingSpot;
import model.Vehicle;
import model.constant.ParkingSpotTier;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot findParkingSpot(ParkingLot parkingLot, Vehicle vehicle, ParkingSpotTier parkingSpotTier);
}
