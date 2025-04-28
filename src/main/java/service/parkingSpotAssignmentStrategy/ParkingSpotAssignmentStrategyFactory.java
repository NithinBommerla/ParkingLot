package service.parkingSpotAssignmentStrategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingSpotAssignmentStrategy() {
        return new LinearParkingSpotAssignmentStrategy();
    }

}
