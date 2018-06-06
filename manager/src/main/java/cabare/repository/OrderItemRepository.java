package cabare.repository;

import cabare.entity.model.Dish;
import cabare.entity.model.OrderItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  OrderItem findByDish_Id(Long dishId);

}