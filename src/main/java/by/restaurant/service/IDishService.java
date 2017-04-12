package by.restaurant.service;

import by.restaurant.model.Dish;
import java.util.List;

public interface IDishService {
    Dish getById(Long id);

    Dish save(final Dish dish);

    void delete(Long id);

    List<Dish> getAll();
}