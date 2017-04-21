package by.restaurant.service;

import by.restaurant.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderService {

    Order getById(Long id);

    Order save(final Order order);

    void delete(Long id);

    List<Order> getAll();

    List<Order> getAllOrdersAfter(Timestamp timestamp);

}