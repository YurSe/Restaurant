package by.restaurant.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Pavel on 09.04.2017.
 */
@Entity
@Table(name = "roles")
public class Role extends SuperClass implements GrantedAuthority {

    @Column(unique = true, nullable = false)
    private String authority;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
