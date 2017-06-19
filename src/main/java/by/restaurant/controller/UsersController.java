package by.restaurant.controller;

import by.restaurant.model.Role;
import by.restaurant.model.User;
import by.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Component
@Scope("session")
public class UsersController implements Serializable {

    @Autowired
    private IUserService userService;

    private User user;

    public List<User> getUsers() {
        return userService.getAll();
    }

    public Boolean IsAdmin(User user) {
        Set<Role> roles = user.getAuthorities();
        if (roles != null) {
            for (Role role : roles) {
                if (role.getAuthority().equals("ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }

    public String Status(User user) {
        return IsAdmin(user) ? "Admin" : "User";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
