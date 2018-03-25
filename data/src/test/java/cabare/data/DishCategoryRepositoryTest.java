package cabare.data;

import cabare.entity.model.DishCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishCategoryRepositoryTest {
    private final String testNameDishCategory = "Fishes";
    private DishCategory dishCategory;


    @Autowired
    private DishCategoryRepository dishCategoryRepository;

    @Before
    public void init() {
        dishCategory = new DishCategory();
        dishCategory.setName(testNameDishCategory);
        dishCategoryRepository.save(dishCategory);
    }

    @Test
    public void testDishCategoryFindById() {
        Long dishCategoryId = dishCategory.getId();
        Optional<DishCategory> result = dishCategoryRepository.findById(dishCategoryId);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(testNameDishCategory);
    }

    @Test
    public void testDishCategoryFindByName() {
        Optional<DishCategory> result = dishCategoryRepository.findByName(testNameDishCategory);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(testNameDishCategory);
    }

    @Test
    public void testDishCategoryGetAll() {
        List<DishCategory> dishCategoryList = dishCategoryRepository.findAll();
        assertThat(dishCategoryList.isEmpty()).isFalse();
        assertThat(dishCategoryList.get(0).getName()).isEqualTo(testNameDishCategory);
    }


}
