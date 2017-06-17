package by.restaurant.controller;

import by.restaurant.model.Category;
import by.restaurant.model.Dish;
import by.restaurant.model.Order;
import by.restaurant.model.Order_dish;
import by.restaurant.service.ICategoryService;
import by.restaurant.service.IDishService;
import by.restaurant.service.IOrderService;
import by.restaurant.util.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Controller
@Scope("session")
public class DishController implements Serializable {

    private final int SPACE_PERIOD_DISH_DESCRIPTION = 23;

    private final int SPACE_PERIOD_DISH_NAME = 20;

    @Autowired
    private IDishService dishService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICategoryService categoryService;

    private Dish dishDelete;

    private Dish dish = new Dish();

    private Map<String, String> categories = new HashMap<String, String>();


    private String selectCategory;

    @PostConstruct
    public void init() {
        List<Category> allCategories = categoryService.getAll();

        for (Category category : allCategories) {
            String name = category.getName();
            categories.put(name, name);
        }
    }

    public void Clear() {
        dish = new Dish();
    }

    public List<Dish> getAllDishes() {
        return dishService.getAll();
    }

    public void SaveDish() {
        dish.setCategory(categoryService.getCategoryByName(selectCategory));
        dish.setName(StringParser.insertPeriodically(dish.getName(), " ", SPACE_PERIOD_DISH_NAME));
        dish.setDescription(StringParser.insertPeriodically(dish.getDescription(), " ", SPACE_PERIOD_DISH_DESCRIPTION));
        dishService.save(dish);
        dish = new Dish();
    }

    private String insertPeriodically(
            String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(
                text.length() + insert.length() * (text.length() / period) + 1);
        int index = 0;
        String prefix = "";
        while (index < text.length()) {
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index,
                    Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }


    public void DeleteDish(Long id) {
        dishService.delete(id);
    }

    public void DishDelete() {
        dishService.delete(dishDelete.getId());
    }


    public Set<Dish> getPopularDishes() {
        return sortByValue(createMap(orderService.getAllOrdersAfter(getTimestampWeekAgoFromNow()))).keySet();
    }

    private Timestamp getTimestampWeekAgoFromNow() {
        Timestamp ts = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, -7);
        ts.setTime(cal.getTime().getTime());
        return ts;
    }

    private Map<Dish, Integer> createMap(List<Order> orders) {

        Map<Dish, Integer> map = new HashMap<>();

        for (Order order : orders) {
            Set<Dish> dishes = new HashSet<>();
            Set<Order_dish> order_dishes = order.getOrder_dishes();

            for (Order_dish order_dish : order_dishes) {
                for (int i = 0; i < order_dish.getCount(); i++)
                    dishes.add(order_dish.getDish());
            }

            for (Dish dish : dishes) {
                Integer i = 1;
                if (map.containsKey(dish)) {
                    i = map.get(dish) + 1;
                }
                map.put(dish, i);
            }
        }
        return map;
    }

    private static Map<Dish, Integer> sortByValue(Map<Dish, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Dish, Integer>> list =
                new LinkedList<Map.Entry<Dish, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Dish, Integer>>() {
            public int compare(Map.Entry<Dish, Integer> o1,
                               Map.Entry<Dish, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Dish, Integer> sortedMap = new LinkedHashMap<Dish, Integer>();
        for (Map.Entry<Dish, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(String selectCategory) {
        this.selectCategory = selectCategory;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public Dish getDishDelete() {
        return dishDelete;
    }

    public void setDishDelete(Dish dishDelete) {
        this.dishDelete = dishDelete;
    }

    public void DishDeleteSet(Dish dishDelete) {
        this.dishDelete = dishDelete;
    }
}
