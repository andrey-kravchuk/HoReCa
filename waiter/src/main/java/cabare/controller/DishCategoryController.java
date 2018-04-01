package cabare.controller;

import cabare.dto.DishCategoryDto;
import cabare.service.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish_category")
public class DishCategoryController {

    @Autowired
    private DishCategoryService dishCategoryService;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void add(@RequestBody DishCategoryDto dishCategoryDto) {
        dishCategoryService.addDishCategory(dishCategoryDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody DishCategoryDto dishCategoryDto) {
        dishCategoryService.updateDishCategory(dishCategoryDto);
    }

    @RequestMapping(value = "/all")
    public List<DishCategoryDto> getDishCategories() {
        return dishCategoryService.getDishCategories();
    }


}
