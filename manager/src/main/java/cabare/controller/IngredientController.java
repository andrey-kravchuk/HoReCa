package cabare.controller;

import cabare.dto.IngredientDto;
import cabare.service.impl.IngredientServiceImpl;
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
  IngredientServiceImpl ingredientServiceImpl;

  @RequestMapping(value = "by_id", method = RequestMethod.GET)
  public IngredientDto findById(@RequestParam(value = "id") Long ingredientId) {
    return ingredientServiceImpl.findIngredientDtoById(ingredientId);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<IngredientDto> getAll(Pageable pageable) {
    return ingredientServiceImpl.getPageOfIngredient(pageable);
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addIngredient(@RequestParam(name = "name") String name,
      @RequestParam(name = "measure_id") Long measureId) {
    ingredientServiceImpl.addNewIngredient(name, measureId);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateIngredient(@RequestParam(name = "id") Long ingredientId,
      @RequestParam(name = "name") String ingredientNewName,
      @RequestParam(name = "measure_id") Long measureId){
    ingredientServiceImpl.updateIngredient(ingredientId, ingredientNewName, measureId);
  }

}
