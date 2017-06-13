package by.restaurant.controller;

import by.restaurant.model.Order;
import by.restaurant.model.Order_dish;
import by.restaurant.model.User;
import by.restaurant.repository.UserRepository;
import by.restaurant.service.IOrderService;
import net.sf.ehcache.search.expression.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Scope("request")
public class OrderController implements Serializable {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getUserOrders() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return orderService.getOrdersByUser(user);
    }

    public Double TotalPrice(Order order) {
        double fullPrice = 0;
        for (Order_dish order_dish : order.getOrder_dishes())
            for (int i = 0; i < order_dish.getCount(); i++)
                fullPrice += order_dish.getDish().getPrice();

        return Math.round(fullPrice * 100.0) / 100.0;
    }

}
