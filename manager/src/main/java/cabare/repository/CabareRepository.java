package cabare.repository;

import cabare.entity.model.Cabare;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabareRepository extends JpaRepository<Cabare, Long> {

  Optional<Cabare> findById(Long cabareId);
}
