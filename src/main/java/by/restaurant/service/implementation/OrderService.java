package by.restaurant.service.implementation;

import by.restaurant.model.Order;
import by.restaurant.model.User;
import by.restaurant.repository.IOrderRepository;
import by.restaurant.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Component
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    public Order getById(Long id) {
        return iOrderRepository.getOne(id);
    }

    @Override
    public Order save(Order order) {
        return iOrderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        iOrderRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAll() {
        return iOrderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersAfter(Timestamp timestamp) {
        return iOrderRepository.getAllOrdersAfter(timestamp);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return iOrderRepository.getOrdersByUser(user);
    }
}
