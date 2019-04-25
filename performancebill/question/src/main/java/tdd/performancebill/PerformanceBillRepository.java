package tdd.performancebill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceBillRepository extends JpaRepository<PerformanceBill, Long> {
}
