package cabare.controller;

import cabare.dto.IngredientDto;
import cabare.entity.model.Ingredient;
import cabare.service.IngredientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/ingredient")
public class IngredientController {

  @Autowired
  IngredientService ingredientService;

  @RequestMapping(value = "by_id", method = RequestMethod.GET)
  public IngredientDto findById(@RequestParam(value = "id") Long ingredientId) {
    Ingredient ingredient = ingredientService.findIngredientById(ingredientId);
    return new IngredientDto(ingredient);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<IngredientDto> getAll(Pageable pageable) {
    return ingredientService.getPageOfIngredient(pageable);
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addIngredient(@RequestParam(name = "name") String name,
      @RequestParam(name = "measure_id") Long measureId) {
    ingredientService.addNewIngredient(name, measureId);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateIngredient(@RequestParam(name = "id") Long ingredientId,
      @RequestParam(name = "name") String ingredientNewName,
      @RequestParam(name = "measure_id") Long measureId){
    ingredientService.updateIngredient(ingredientId, ingredientNewName, measureId);
  }

}
