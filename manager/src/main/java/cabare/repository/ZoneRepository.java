package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Zone;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

  Optional<Zone> findByIdAndCabare(Long zoneId, Cabare cabare);
}
