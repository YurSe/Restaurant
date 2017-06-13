package by.restaurant.repository;

import by.restaurant.model.Order;
import by.restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.timestamp >= :timestamp")
    List<Order> getAllOrdersAfter(@Param("timestamp") Timestamp timestamp);

    @Query("select o from Order o where o.user = :user ORDER BY o.timestamp DESC")
    List<Order> getOrdersByUser(@Param("user") User user);

}
