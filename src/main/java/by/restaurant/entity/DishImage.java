package by.restaurant.entity;

import javax.persistence.*;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
@javax.persistence.Table(name = "dish_image", schema = "public", catalog = "restaurant")
public class DishImage {
    private long dishId;
    private long imageId;
    private Dish dishByDishId;
    private Image imageByImageId;

    @Basic
    @Column(name = "dish_id", nullable = false)
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "image_id", nullable = false)
    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishImage dishImage = (DishImage) o;

        if (dishId != dishImage.dishId) return false;
        if (imageId != dishImage.imageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (dishId ^ (dishId >>> 32));
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
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
    @JoinColumn(name = "image_id", referencedColumnName = "image_id", nullable = false)
    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }
}
