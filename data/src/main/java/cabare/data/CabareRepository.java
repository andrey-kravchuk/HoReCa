package cabare.data;

import cabare.entity.model.Cabare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabareRepository extends JpaRepository<Cabare, Long> {

  Optional<Cabare> findById(Long cabareId);
}