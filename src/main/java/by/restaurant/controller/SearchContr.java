package by.restaurant.controller;

import by.restaurant.model.Dish;
import by.restaurant.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pavel on 21.04.2017.
 */
@Controller
@Scope("session")
public class SearchContr implements Serializable{

    @Autowired
    private IDishService iDishService;

    private List<Dish> searchedDishes;

    private String searchToken;

    private String categoryName;

    public SearchContr() {
    }

    public String getSearchToken() {
        return searchToken;
    }

    public void setSearchToken(String searchToken) {
        this.searchToken = searchToken;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void search() {
        if(categoryName.equals("") && searchToken.equals("")){
            searchedDishes = iDishService.getAll();
        }
        else if (!categoryName.equals("") && searchToken.equals("")){
            searchedDishes = iDishService.getByCategoryName(categoryName);
        }
        else if (categoryName.equals("") && !searchToken.equals("")) {
            searchedDishes = iDishService.getBySearchToken(searchToken);
        }
        else {
            searchedDishes = iDishService.getByCategoryNameAndSearchToken(categoryName,searchToken);
        }
    }

    public void test() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/pages/index.xhtml");
        }catch (Exception e) {
            e.printStackTrace();
    }
    }

    public List<Dish> getSearchedDishes() {
        return searchedDishes;
    }

    public void setSearchedDishes(List<Dish> searchedDishes) {
        this.searchedDishes = searchedDishes;
    }

}
