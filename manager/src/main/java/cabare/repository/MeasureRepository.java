package cabare.repository;

import cabare.entity.model.Measure;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long>{

  Optional<Measure> findById(Long measureId);

  List<Measure> findAll();
}
