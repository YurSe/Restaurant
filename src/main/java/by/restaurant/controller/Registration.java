package by.restaurant.controller;

import by.restaurant.model.User;
import by.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserContr userContr;
    @Autowired
    private UserService userService;

    public void showRegisteredUserNotification() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String labelCreatedWithSuccess = (String) ec.getSessionMap().get("registeredUserWithSuccess");
        if (labelCreatedWithSuccess != null && labelCreatedWithSuccess.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messageRegisteredUser", new FacesMessage("Successful", "Thank you very much, registration was successful." +
                    " Please log in now!"));
            ec.getSessionMap().remove("registeredUserWithSuccess");
        }
    }

    private void goToAuthorizationPage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().put("registeredUserWithSuccess", "true");
        context.redirect(context.getRequestContextPath() + "/pages/login.xhtml");
    }

    public void register() throws IOException {
        User user = new User();
        user.setBirthday(userContr.getBirthday());
        user.setSubscription(userContr.isHasSubscription());
        user.setPhoneNumber(userContr.getPhoneNumber());
        user.setName(userContr.getName());
        user.setAddress(userContr.getAddress());
        user.setPassword(bCryptPasswordEncoder.encode(userContr.getPassword()));
        user.setEmail(userContr.getEmail());
        userService.registerUser(user);
        goToAuthorizationPage();
    }
}

