package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Dish {
    private int dishId;
    private String name;
    private long image;
    private double price;
    private long categoryId;
    private Category categoryByCategoryId;
    private Collection<DishDiscount> dishDiscountsByDishId;
    private Collection<DishImage> dishImagesByDishId;
    private Collection<OrderDish> orderDishesByDishId;

    @Id
    @Column(name = "dish_id", nullable = false)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image", nullable = false)
    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (dishId != dish.dishId) return false;
        if (image != dish.image) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (categoryId != dish.categoryId) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dishId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (image ^ (image >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @OneToMany(mappedBy = "dishByDishId")
    public Collection<DishDiscount> getDishDiscountsByDishId() {
        return dishDiscountsByDishId;
    }

    public void setDishDiscountsByDishId(Collection<DishDiscount> dishDiscountsByDishId) {
        this.dishDiscountsByDishId = dishDiscountsByDishId;
    }

    @OneToMany(mappedBy = "dishByDishId")
    public Collection<DishImage> getDishImagesByDishId() {
        return dishImagesByDishId;
    }

    public void setDishImagesByDishId(Collection<DishImage> dishImagesByDishId) {
        this.dishImagesByDishId = dishImagesByDishId;
    }

    @OneToMany(mappedBy = "dishByDishId")
    public Collection<OrderDish> getOrderDishesByDishId() {
        return orderDishesByDishId;
    }

    public void setOrderDishesByDishId(Collection<OrderDish> orderDishesByDishId) {
        this.orderDishesByDishId = orderDishesByDishId;
    }
}
