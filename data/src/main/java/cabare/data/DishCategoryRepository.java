package cabare.data;

import cabare.entity.model.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {
    Optional<DishCategory> findById(Long dishCategoryId);

    Optional<DishCategory> findByName(String dishCategoryName);

    @Query("select d from DishCategory d")
    List<DishCategory> getAll();

}
