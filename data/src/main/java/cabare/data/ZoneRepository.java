package cabare.data;

import cabare.entity.model.Cabare;
import cabare.entity.model.Zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

  Optional<Zone> findByIdAndCabare(Long zoneId, Cabare cabare);

  @Query("select z from Zone z where z.cabare = ?1")
  List<Zone> getAll(Cabare cabare);

}
