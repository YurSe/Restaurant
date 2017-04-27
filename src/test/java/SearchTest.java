import by.restaurant.model.Category;
import by.restaurant.model.Dish;
import by.restaurant.repository.CategoryRepository;
import by.restaurant.service.IDishService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 27.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*/applicationContext.xml")
@Transactional
public class SearchTest {

    @Autowired
    private IDishService iDishService;
    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    private Category subcategory;

    private String randomToken;

    private String getRandomToken() {
        return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    }

    public void initializeDishes() {
        Dish dish = new Dish();
        dish.setName("1" + randomToken);
        dish.setPrice(1);
        dish.setImage("1");
        dish.setCategory(category);
        iDishService.save(dish);
        Dish secondDish = new Dish();
        secondDish.setName(getRandomToken());
        secondDish.setPrice(2);
        secondDish.setImage("2");
        secondDish.setCategory(category);
        iDishService.save(secondDish);
        Dish thirdDish = new Dish();
        thirdDish.setName("3" + randomToken);
        thirdDish.setImage("3");
        thirdDish.setPrice(3);
        thirdDish.setCategory(subcategory);
        iDishService.save(thirdDish);
    }

    public void initializeCategories() {
        category = new Category();
        category.setName(randomToken);
        categoryRepository.save(category);
        subcategory = new Category();
        String randomCategoryName = getRandomToken();
        subcategory.setName(randomCategoryName);
        categoryRepository.save(subcategory);
    }

    @Before
    public void init() {
        randomToken = getRandomToken();
        initializeCategories();
        initializeDishes();
    }

    @Test
    public void getDishesByCategoryName() throws Exception {
        List<Dish> dishes = iDishService.getByCategoryName(randomToken);
        Assert.assertNotNull(dishes);
        Assert.assertEquals(2, dishes.size());
    }

    @Test
    public void getDishesBySearchToken() throws Exception {
        List<Dish> dishes = iDishService.getBySearchToken(randomToken);
        Assert.assertNotNull(dishes);
        Assert.assertEquals(2, dishes.size());
    }

    @Test
    public void getDishesByCategoryNameAndSearchToken() throws Exception {
        List<Dish> dishes = iDishService.getByCategoryNameAndSearchToken(randomToken, randomToken);
        Assert.assertNotNull(dishes);
        Assert.assertEquals(1, dishes.size());
    }

    @Test
    public void getAllDishes() throws Exception {
        List<Dish> dishes = iDishService.getAll();
        Assert.assertNotNull(dishes);
    }

    @After
    public void clearCategories() {
        this.category = null;
        this.subcategory = null;
    }
}
