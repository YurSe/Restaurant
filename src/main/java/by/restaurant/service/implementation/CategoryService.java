package by.restaurant.service.implementation;

import by.restaurant.model.Category;
import by.restaurant.repository.ICategoryRepository;
import by.restaurant.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Category getById(Long id) {
        return iCategoryRepository.getOne(id);
    }

    @Override
    public Category save(Category category) {
        return iCategoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        iCategoryRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return iCategoryRepository.getCategoryByName(name);
    }
}