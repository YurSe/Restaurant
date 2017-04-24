package by.restaurant.controller;

import by.restaurant.model.Category;
import by.restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 21.04.2017.
 */
@Controller
@Scope("session")
public class CategoryView implements Serializable {

    @Autowired
    private CategoryService categoryService;

    List<Category> categories;

    List<SelectItem> selectCategories;

    @PostConstruct
    public void init() {
        selectCategories = new ArrayList<>();
        categories = categoryService.getAll();
        for (Category category : categories)
        selectCategories.add(new SelectItem(category.getName(),category.getName()));
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<SelectItem> getSelectCategories() {
        return selectCategories;
    }

    public void setSelectCategories(List<SelectItem> selectCategories) {
        this.selectCategories = selectCategories;
    }
}
