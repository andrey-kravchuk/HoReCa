package cabare.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Zone;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishRepositoryTest {

  private Dish dish, dish1;
  private DishCategory dishCategory;
  private Cabare cabare;
  private Pageable pageable;
  private Page<Dish> result;
  private List<Dish> testList;
  private LocalDate date, date1;
  private int dayOfDate;

  @Autowired
  private DishRepository dishRepository;

  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  @Autowired
  private CabareRepository cabareRepository;

  @Autowired
  private ZoneRepository zoneRepository;

  @Before
  public void init() {
    dish = new Dish();
    Zone zone = new Zone();
    zoneRepository.save(zone);
    cabare = new Cabare();
    cabareRepository.save(cabare);
    dish.setCabare(cabare);
    dishCategory = new DishCategory();
    dishCategory.setZone(zone);
    dishCategoryRepository.save(dishCategory);
    String testNameDish = "Салат Зима";
    dish.setName(testNameDish);
    dish.setDishCategory(dishCategory);
    dish.setStartDay(0);
    dish.setEndDay(59);
    dishRepository.save(dish);

    dish1 = new Dish();
    dish1.setCabare(cabare);
    String testNameDish1 = "Салат Лето";
    dish1.setName(testNameDish1);
    dish1.setDishCategory(dishCategory);
    dish1.setStartDay(0);
    dish1.setEndDay(59);

    date = LocalDate.parse("2017-01-20");
    dayOfDate = date.getDayOfYear();
    date1 = LocalDate.parse("2017-03-20");
    pageable = new PageRequest(0, 10);
    testList = new LinkedList<>();
  }

  @Test
  //happy
  public void findDishByIdAndCabareInPeriod() {
    Long dishId = dish.getId();
    Optional<Dish> result = dishRepository.findByIdAndCabare(dishId, dayOfDate, cabare);
    assertThat(result.isPresent()).isTrue();
    assertEquals(result.get(), dish);
  }

  @Test
  //not happy
  public void findDishByIdAndCabareNotInPeriod() {
    Long dishId = dish.getId();
    dayOfDate = date1.getDayOfYear();
    Optional<Dish> result = dishRepository.findByIdAndCabare(dishId, dayOfDate, cabare);
    assertThat(result.isPresent()).isFalse();
  }

  @Test
  //one dish
  public void testFindDishByDishCategory() {
    testList.add(dish);
    result = dishRepository.findDishesByDishCategory(dishCategory, dayOfDate, pageable);
    assertEquals(result.getContent(), testList);
  }

  @Test
  //several dishes
  public void testFindDishesByDishCategory() {
    testList.add(dish);
    testList.add(dish1);
    dishRepository.save(dish1);
    result = dishRepository.findDishesByDishCategory(dishCategory, dayOfDate, pageable);
    assertEquals(result.getContent(), testList);
  }

  @Test
  //several dishes, several pages
  public void testFindDishesByDishCategoryByPages() {
    testList.add(dish);
    testList.add(dish1);
    pageable = new PageRequest(0, 1);
    dishRepository.save(dish1);
    result = dishRepository.findDishesByDishCategory(dishCategory, dayOfDate, pageable);
    assertEquals(result.getContent().get(0), testList.get(0));
    pageable = new PageRequest(1, 1);
    result = dishRepository.findDishesByDishCategory(dishCategory, dayOfDate, pageable);
    assertEquals(result.getContent().get(0), testList.get(1));
  }
}

