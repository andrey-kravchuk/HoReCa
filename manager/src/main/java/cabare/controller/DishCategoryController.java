package cabare.controller;

import cabare.dto.DishCategoryDto;
import cabare.service.DishCategoryService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/dish_category")
public class DishCategoryController {

  @Autowired
  private DishCategoryService dishCategoryService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void addDishCategory(@Valid @RequestBody DishCategoryDto dishCategoryDto) {
    dishCategoryService.addDishCategory(dishCategoryDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateDishCategory(@Valid @RequestBody DishCategoryDto dishCategoryDto) {
    dishCategoryService.updateDishCategory(dishCategoryDto);
  }
}

