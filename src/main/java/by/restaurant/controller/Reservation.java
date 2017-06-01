package by.restaurant.controller;

import by.restaurant.model.Dish;
import by.restaurant.model.Order;
import by.restaurant.model.User;
import by.restaurant.repository.UserRepository;
import by.restaurant.service.IOrderService;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("request")
public class Reservation implements Serializable{

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UserRepository userRepository;

    private Date minDate = new Date();

    private Date date;

    private Date time;

    private Integer guestCount;

    private Set<Dish> dishes = new HashSet<>();

    public Reservation() {
    }

    public int getHoursFromTime () {
        minDate = new Date();
        return minDate.getHours();
    }

    public int getMinutesFromTime() {
        minDate = new Date();
        return minDate.getMinutes();
    }

    public int getSecondsFromTime() {
        minDate = new Date();
        return minDate.getSeconds();
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public void AddDish(Dish dish) {
        dishes.add(dish);
    }

    public void RemoveDish(Dish dish) {
        dishes.remove(dish);
    }


    public boolean IsDishesEmpty() {
        return dishes.isEmpty();
    }


    private Date dateTime(Date date, Date time) {
        return new Date(
                date.getYear(), date.getMonth(), date.getDay(),
                time.getHours(), time.getMinutes(), time.getSeconds()
        );
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public void dateChange(SelectEvent event) {
        Date date = (Date)event.getObject();
    }

    public void timeChange(SelectEvent event) {
        Date date = (Date)event.getObject();
    }

    public void showCreatedOrderNotification(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String labelCreatedWithSuccess = (String) ec.getSessionMap().get("createdWithSuccess");
        if (labelCreatedWithSuccess!=null && labelCreatedWithSuccess.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messageCreatedOrder", new FacesMessage("Successful",  "Thank you very much, the order will be reviewed within 3 minutes") );
            ec.getSessionMap().remove("createdWithSuccess");
        }
    }

    private void showUserNotFoundNotification() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messageNotFoundUser", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error","User not found") );
    }

    private void goToMenuPage() throws IOException{
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().put("createdWithSuccess","true");
        context.redirect(context.getRequestContextPath() + "/pages/menu.xhtml");
    }

    public void createOrder() throws IOException {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user == null) {
            showUserNotFoundNotification();
            return;
        }
        Order order = new Order();
        order.setTimestamp(new Timestamp(dateTime(date, time).getTime()));
        order.setGuestCount(guestCount);
        order.setUser(user);
        order.setDishes(dishes);
        iOrderService.save(order);
        goToMenuPage();
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

}
