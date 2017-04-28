package by.restaurant.repository;

import by.restaurant.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name = :name")
    Category getCategoryByName(@Param("name") String name);

}