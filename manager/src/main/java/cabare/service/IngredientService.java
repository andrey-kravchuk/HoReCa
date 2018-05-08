package cabare.service;

import cabare.dto.IngredientDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IngredientService {

  IngredientDto findIngredientById(Long ingredientId);

  List<IngredientDto> getPageOfIngredient(Pageable pageable);

  void addNewIngredient(String newIngredientName);

  void updateIngredient(Long ingredientId, String ingredientName);
}
