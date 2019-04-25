package tdd.customer.share.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("找不到ID为'"+ id + "'的客户!");
    }
}
