package farm_sharing.exceptions;

public class NothingAvailableForReservationException extends RuntimeException {
    public NothingAvailableForReservationException() {
        super("No items are available for reservation");
    }
}
