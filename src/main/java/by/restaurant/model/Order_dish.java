package by.restaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_dish")
@IdClass(Order_dishPK.class)
public class Order_dish implements Serializable {

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Id
    @Column(name = "dish_id", nullable = false)
    private Long dishId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", nullable = false, insertable = false, updatable = false)
    private Dish dish;

    @Column(name = "Count")
    private Integer count;


    public Order_dish() {
    }

    public Order_dish(Long orderId, Long dishId, Order order, Dish dish, Integer count) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.order = order;
        this.dish = dish;
        this.count = count;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
