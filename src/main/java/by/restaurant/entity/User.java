package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class User {
    private int userId;
    private int name;
    private String phoneNumber;
    private String password;
    private String email;
    private int address;
    private int vip;
    private double balance;
    private Collection<Bill> billsByUserId;
    private Collection<Order> ordersByUserId;
    private Collection<Token> tokensByUserId;
    private Collection<UserRole> userRolesByUserId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, length = -1)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = false)
    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Basic
    @Column(name = "vip", nullable = false)
    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (name != user.name) return false;
        if (address != user.address) return false;
        if (vip != user.vip) return false;
        if (Double.compare(user.balance, balance) != 0) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + name;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + address;
        result = 31 * result + vip;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Bill> getBillsByUserId() {
        return billsByUserId;
    }

    public void setBillsByUserId(Collection<Bill> billsByUserId) {
        this.billsByUserId = billsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Order> getOrdersByUserId() {
        return ordersByUserId;
    }

    public void setOrdersByUserId(Collection<Order> ordersByUserId) {
        this.ordersByUserId = ordersByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Token> getTokensByUserId() {
        return tokensByUserId;
    }

    public void setTokensByUserId(Collection<Token> tokensByUserId) {
        this.tokensByUserId = tokensByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserRole> getUserRolesByUserId() {
        return userRolesByUserId;
    }

    public void setUserRolesByUserId(Collection<UserRole> userRolesByUserId) {
        this.userRolesByUserId = userRolesByUserId;
    }
}
