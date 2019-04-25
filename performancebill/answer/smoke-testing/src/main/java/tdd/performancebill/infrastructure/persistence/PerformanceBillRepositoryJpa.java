package tdd.performancebill.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.performancebill.domain.model.performancebill.PerformanceBill;
import tdd.performancebill.domain.model.performancebill.PerformanceBillRepository;

public interface PerformanceBillRepositoryJpa
        extends JpaRepository<PerformanceBill, Long>, PerformanceBillRepository {
}
