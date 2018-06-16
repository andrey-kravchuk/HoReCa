package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {

  Optional<DishCategory> findByIdAndCabare(Long dishCategoryId, Cabare cabare);

  @Query("select d from DishCategory d where d.cabare = ?1")
  List<DishCategory> getAll(Cabare cabare);

  @Query("select d from DishCategory d where d.zone = ?1 and d.cabare = ?2")
  List<DishCategory> getAllByZone(Zone zone, Cabare cabare);

}
