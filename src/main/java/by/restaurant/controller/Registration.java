package by.restaurant.controller;

import by.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by Pavel on 15.04.2017.
 */
@Component
@Scope("request")
public class Registration {

    @Autowired
    private User user;
    @Autowired
    private UserService userService;

    public void register() throws IOException {
        try {
            userService.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/pages/index.xhtml");
        }
    }
