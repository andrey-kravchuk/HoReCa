package cabare.data;

import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishRepositoryTest {
    private final String testNameDish = "Салат Зима";
    private final String testNameDish1 = "Салат Лето";
    private Dish dish, dish1;
    private DishCategory dishCategory;
    private Pageable pageable;
    private Page<Dish> result;
    List<Dish> testList;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishCategoryRepository dishCategoryRepository;

    @Before
    public void init() {
        dish = new Dish();
        dish1 = new Dish();
        dishCategory = new DishCategory();
        dishCategoryRepository.save(dishCategory);
        dish.setName(testNameDish);
        dish1.setName(testNameDish1);
        dish.setDishCategory(dishCategory);
        dish1.setDishCategory(dishCategory);
        dishRepository.save(dish);
        pageable = new PageRequest(0, 10);
        testList = new LinkedList<>();
    }

    @Test
    public void testDishFindById() {
        Long dishId = dish.getId();
        Optional<Dish> result = dishRepository.findById(dishId);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(testNameDish);
    }

    @Test
    //one dish
    public void testFindDishByDishCategory() {
        testList.add(dish);
        result = dishRepository.findDishesByDishCategory(dishCategory, pageable);
        assertEquals(result.getContent(), testList);
    }

    @Test
    //several dishes
    public void testFindDishesByDishCategory() {
        testList.add(dish);
        testList.add(dish1);
        dishRepository.save(dish1);
        result = dishRepository.findDishesByDishCategory(dishCategory, pageable);
        assertEquals(result.getContent(), testList);
    }
}

