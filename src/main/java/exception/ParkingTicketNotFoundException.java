package exception;

public class ParkingTicketNotFoundException extends RuntimeException {
    public ParkingTicketNotFoundException(String message) {
        super(message);
    }
}
