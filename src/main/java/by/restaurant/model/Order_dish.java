package by.restaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_dish")
public class Order_dish implements Serializable {

    @Id
    private long orderId;

    @Id
    private long dishId;

    @Column(name="Count")
    private Integer count;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="ORDERID", referencedColumnName="ID")
    private Order order;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="DISHID", referencedColumnName="ID")
    private Dish dish;

    public Order_dish() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

}
