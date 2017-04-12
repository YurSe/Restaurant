package by.restaurant.repository;

import by.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<Dish, Long> {
}
