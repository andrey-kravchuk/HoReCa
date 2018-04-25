package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Zone;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository {

  @Query("select z from Zone z where z.id = ?1 and z.cabare = ?2")
  Optional<Zone> findZoneByIdAndCabare(Long zoneId, Cabare cabare);

}
