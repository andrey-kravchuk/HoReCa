package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.DishWaiter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {

  DishWaiter findByid(Long dishId);

  List<DishDto> getDishesByCategory(Long dishCategoryId, Pageable pageable);

  List<DishDto> getStopList();
}
