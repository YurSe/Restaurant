package by.restaurant.repository;


import by.restaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "SELECT * FROM Dish D JOIN CATEGORY C ON D.CATEGORY_ID = C.ID WHERE C.NAME=?1",
            nativeQuery = true)
    List<Dish> findByCategoryName(String categoryName);

    @Query(value = "SELECT * FROM Dish D JOIN CATEGORY C ON D.CATEGORY_ID = C.ID WHERE C.NAME=?1 and lower(D.NAME) like ?2",
            nativeQuery = true)
    List<Dish> findByCategoryNameAndSearchToken(String categoryName, String searchToken);

    List<Dish> findAll();
}
