package cabare.service;

import cabare.dto.IngredientDto;
import cabare.entity.model.Ingredient;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IngredientService {

  IngredientDto findIngredientDtoById(Long ingredientId);

  Ingredient findIngredientById(Long ingredientId);

  List<IngredientDto> getPageOfIngredient(Pageable pageable);

  void addNewIngredient(String newIngredientName, Long measureId);

  void updateIngredient(Long ingredientId, String ingredientName, Long measureId);
}
