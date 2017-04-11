package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Discount {
    private int discountId;
    private double percent;
    private Collection<DishDiscount> dishDiscountsByDiscountId;

    @Id
    @Column(name = "discount_id", nullable = false)
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "percent", nullable = false, precision = 0)
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (discountId != discount.discountId) return false;
        if (Double.compare(discount.percent, percent) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = discountId;
        temp = Double.doubleToLongBits(percent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "discountByDiscountId")
    public Collection<DishDiscount> getDishDiscountsByDiscountId() {
        return dishDiscountsByDiscountId;
    }

    public void setDishDiscountsByDiscountId(Collection<DishDiscount> dishDiscountsByDiscountId) {
        this.dishDiscountsByDiscountId = dishDiscountsByDiscountId;
    }
}
