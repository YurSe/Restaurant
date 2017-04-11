package by.restaurant.controller;

import by.restaurant.entity.Dish;
import by.restaurant.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

@Controller
@Scope("request")
public class DishContr implements Serializable {

    @Autowired
    private IDishService dishService;

    public List<Dish> getDishes() {
        return dishService.getAll();
    }
}
