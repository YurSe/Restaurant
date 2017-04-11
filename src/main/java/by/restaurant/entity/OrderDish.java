package by.restaurant.entity;

import javax.persistence.*;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
@javax.persistence.Table(name = "order_dish", schema = "public", catalog = "restaurant")
public class OrderDish {
    private long orderId;
    private long dishId;
    private Order orderByOrderId;
    private Dish dishByDishId;

    @Basic
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "dish_id", nullable = false)
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDish orderDish = (OrderDish) o;

        if (orderId != orderDish.orderId) return false;
        if (dishId != orderDish.dishId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (dishId ^ (dishId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", nullable = false)
    public Dish getDishByDishId() {
        return dishByDishId;
    }

    public void setDishByDishId(Dish dishByDishId) {
        this.dishByDishId = dishByDishId;
    }
}
