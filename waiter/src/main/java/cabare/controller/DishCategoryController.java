package cabare.controller;

import cabare.dto.DishCategoryDto;
import cabare.service.DishCategoryService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waiter/dish_category")
public class DishCategoryController {

  @Autowired
  private DishCategoryService dishCategoryService;

  @RequestMapping(value = "/all")
  public List<DishCategoryDto> getDishCategories() {
    return dishCategoryService.getDishCategories();
  }

  @RequestMapping(value = "/by_zone")
  public List<DishCategoryDto> getCategoriesByZone(
      @NotNull(message = "zone_id should be specified")
      @Min(value = 1, message = "zone_id cannot be less than 1")
      @RequestParam(name = "zone_id") Long zoneId) {
    return dishCategoryService.getCategotiesByZone(zoneId);
  }
}
