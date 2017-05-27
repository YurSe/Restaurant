package by.restaurant.controller;

import by.restaurant.model.Dish;
import by.restaurant.model.Order;
import by.restaurant.service.IOrderService;
import by.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@Scope("session")
public class OrderController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private IOrderService orderService;

    private Order order = new Order();

    @PostConstruct
    private void init() {
        order = new Order();

        order.setGuestCount(1);

        Set<Dish> dishes = new HashSet<>();
        order.setDishes(dishes);
    }


    public void AddDish(Dish dish) {
        order.getDishes().add(dish);
    }

    public void RemoveDish(Dish dish) {
        order.getDishes().remove(dish);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void MakeOrder() {
        if (!order.getDishes().isEmpty()) {
            User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            by.restaurant.model.User user = userService.findByName(userSpring.getUsername());
            order.setUser(user);
            order.setTimestamp(new Timestamp(new Date().getTime()));
            orderService.save(order);
            init();
        }
    }

    public boolean IsDishesEmpty() {
        return order.getDishes().isEmpty();
    }


}
