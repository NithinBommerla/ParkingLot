package exception;

public class ParkingGateNotFoundException extends RuntimeException {
    public ParkingGateNotFoundException(String message) {
        super(message);
    }
}
