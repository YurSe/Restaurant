package by.restaurant.service.implementation;

import by.restaurant.model.Dish;
import by.restaurant.repository.IDishRepository;
import by.restaurant.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DishService implements IDishService {
    @Autowired
    private IDishRepository iDishRepository;

    @Override
    public Dish getById(Long id) {
        return iDishRepository.getOne(id);
    }

    @Override
    public Dish save(Dish dish) {
        return iDishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        iDishRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Dish> getAll() {
        return iDishRepository.findAll();
    }
}
