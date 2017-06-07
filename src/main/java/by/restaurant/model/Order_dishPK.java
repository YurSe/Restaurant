package by.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Order_dishPK implements Serializable {

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Id
    @Column(name = "dish_id", nullable = false)
    private Long dishId;

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
}