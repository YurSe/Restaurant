package by.restaurant.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("session")
public class SpringUserController implements Serializable {

    private static final long serialVersionUID = 1L;

    public String CurrentUserName() {
        User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public String CurrentUserRole() {
        User user;
        try {
            user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return "ROLE_ANONYMOUS";
        }
        return user.getAuthorities().iterator().next().toString();
    }

    public boolean IsAdmin() {
        if (CurrentUserRole().equals("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    public boolean IsAuthorized() {
        if (CurrentUserRole().equals("ROLE_ADMIN")
                || CurrentUserRole().equals("ROLE_USER")) {
            return true;
        }
        return false;
    }

    public String Account() {
        if (CurrentUserRole().equals("ROLE_ADMIN")
                || CurrentUserRole().equals("ROLE_USER")) {
            return "/view/user/account?faces-redirect=true";
        }
        return "";
    }

}