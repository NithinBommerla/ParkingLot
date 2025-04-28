package service.billAmountCalculationStrategy;

import model.constant.ParkingSpotTier;
import model.constant.VehicleType;

public class LinearBillAmountCalculationStrategy implements BillAmountCalculationStrategy{
    @Override
    public double generateOverallBill(long duration, VehicleType vehicleType, ParkingSpotTier parkingSpotTier) {

        return duration; // 1 Rupee per second
    }
}
