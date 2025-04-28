package service.billAmountCalculationStrategy;

import model.constant.ParkingSpotTier;
import model.constant.VehicleType;

public interface BillAmountCalculationStrategy {
    double generateOverallBill(long duration, VehicleType vehicleType, ParkingSpotTier parkingSpotTier);
}
