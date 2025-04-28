package service.strategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingSpotAssignmentStrategy() {
        return new LinearParkingSpotAssignmentStrategy();
    }

}
