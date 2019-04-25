package tdd.playstatement.domain.model.playstatement;
import org.springframework.data.jpa.repository.JpaRepository;
import tdd.playstatement.domain.model.playstatement.PlayStatement;

public interface PlayStatementRepository extends JpaRepository<PlayStatement, Long> {
}
