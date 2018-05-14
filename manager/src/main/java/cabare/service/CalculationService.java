package cabare.service;

import cabare.dto.CalculationDto;
import java.util.Date;

public interface CalculationService {

  CalculationDto findByDishId(Long dishId);

  void addCalculation(Long number, Date date, Long dishId, Long ingredientId, Double quantity);

  void updateCalculation(Long calculationId, Long number, Date date, Long dishId, Long ingredientId, Double quantity);

}
