package tdd.customer.infrastructure.persistence;
import tdd.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import tdd.customer.domain.model.CustomerRepository;

public interface CustomerRepositoryJpa
        extends JpaRepository<Customer, Long>, CustomerRepository {
}
