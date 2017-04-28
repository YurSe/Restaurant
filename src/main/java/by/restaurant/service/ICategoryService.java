package by.restaurant.service;

import by.restaurant.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getById(Long id);

    Category save(final Category category);

    void delete(Long id);

    List<Category> getAll();

    Category getCategoryByName(String name);

}
