package by.restaurant.entity;

import javax.persistence.*;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
@javax.persistence.Table(name = "dish_discount", schema = "public", catalog = "restaurant")
public class DishDiscount {
    private long dishId;
    private long discountId;
    private Dish dishByDishId;
    private Discount discountByDiscountId;

    @Basic
    @Column(name = "dish_id", nullable = false)
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "discount_id", nullable = false)
    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishDiscount that = (DishDiscount) o;

        if (dishId != that.dishId) return false;
        if (discountId != that.discountId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (dishId ^ (dishId >>> 32));
        result = 31 * result + (int) (discountId ^ (discountId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", nullable = false)
    public Dish getDishByDishId() {
        return dishByDishId;
    }

    public void setDishByDishId(Dish dishByDishId) {
        this.dishByDishId = dishByDishId;
    }

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = false)
    public Discount getDiscountByDiscountId() {
        return discountByDiscountId;
    }

    public void setDiscountByDiscountId(Discount discountByDiscountId) {
        this.discountByDiscountId = discountByDiscountId;
    }
}
