package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Category {
    private int categoryId;
    private String name;
    private Collection<Dish> dishesByCategoryId;

    @Id
    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<Dish> getDishesByCategoryId() {
        return dishesByCategoryId;
    }

    public void setDishesByCategoryId(Collection<Dish> dishesByCategoryId) {
        this.dishesByCategoryId = dishesByCategoryId;
    }
}
