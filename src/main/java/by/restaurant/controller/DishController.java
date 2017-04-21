package by.restaurant.controller;

import by.restaurant.model.Dish;
import by.restaurant.model.Order;
import by.restaurant.service.IDishService;
import by.restaurant.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Controller
@Scope("request")
public class DishController implements Serializable {

    @Autowired
    private IDishService dishService;

    @Autowired
    private IOrderService orderService;

    public List<Dish> getDishes() {
        return dishService.getAll();
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
        Map<Dish, Integer> map = new HashMap<Dish, Integer>();

        for (Order order : orders) {
            Set<Dish> dishes = order.getDishes();
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


}
