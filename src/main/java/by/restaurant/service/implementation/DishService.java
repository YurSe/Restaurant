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
    public Dish save(Dish film) {
        return iDishRepository.save(film);
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

    @Transactional(readOnly = true)
    @Override
    public List<Dish> getByCategoryName(String categoryName) {
        return iDishRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Dish> getByCategoryNameAndSearchToken(String categoryName, String searchToken) {
        return iDishRepository.findByCategoryNameAndSearchToken(categoryName, "%" + searchToken + "%");
    }

    @Override
    public List<Dish> getBySearchToken(String searchToken) {
        return iDishRepository.findBySearchToken("%"+searchToken+"%");
    }

}
