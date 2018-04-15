package cabare.controller;

import cabare.dto.DishCategoryDto;
import cabare.service.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
