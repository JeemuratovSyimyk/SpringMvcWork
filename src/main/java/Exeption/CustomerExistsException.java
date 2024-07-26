package Exeption;

public class CustomerExistsException extends CustomException {
    public CustomerExistsException(String message) {
        super(message);
    }
}
