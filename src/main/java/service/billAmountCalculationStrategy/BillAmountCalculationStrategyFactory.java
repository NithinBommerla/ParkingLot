package service.billAmountCalculationStrategy;

public class BillAmountCalculationStrategyFactory {
    public static BillAmountCalculationStrategy getBillAmountCalculationStrategy() {
        return new LinearBillAmountCalculationStrategy();
    }

}
