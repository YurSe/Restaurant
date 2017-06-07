package by.restaurant.controller;

import by.restaurant.model.Dish;
import by.restaurant.model.Order;
import by.restaurant.model.Order_dish;
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
import java.util.*;

@Component
@Scope("session")
public class Reservation implements Serializable {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UserRepository userRepository;

    private Date minDate = new Date();

    private Date date;

    private Date time;

    private Integer guestCount;

    private final int MIN_HOUR = 9;

    private final int MIN_MINUTE = 0;

    private final int MIN_SECOND = 0;

    private List<Dish> dishes = new ArrayList<>();

    public Reservation() {
    }

    public int getHoursFromTime() {
        if (date != null && date.after(new Date())) {
            return MIN_HOUR;
        }
        minDate = new Date();
        return minDate.getHours();
    }

    public int getMinutesFromTime() {
        if (date != null && date.after(new Date())) {
            return MIN_MINUTE;
        }
        minDate = new Date();
        return minDate.getMinutes();
    }

    public int getSecondsFromTime() {
        if (date != null && date.after(new Date())) {
            return MIN_SECOND;
        }
        minDate = new Date();
        return minDate.getSeconds();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
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
        date = (Date) event.getObject();
    }

    public void timeChange(SelectEvent event) {
        time = (Date) event.getObject();
    }

    public void showCreatedOrderNotification() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String labelCreatedWithSuccess = (String) ec.getSessionMap().get("createdWithSuccess");
        if (labelCreatedWithSuccess != null && labelCreatedWithSuccess.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messageCreatedOrder", new FacesMessage("Successful", "Thank you very much, the order will be reviewed within 3 minutes"));
            ec.getSessionMap().remove("createdWithSuccess");
        }
    }

    private void showUserNotFoundNotification() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messageNotFoundUser", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User not found"));
    }

    private void goToMenuPage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().put("createdWithSuccess", "true");
        context.redirect(context.getRequestContextPath() + "/pages/menu.xhtml");
    }

    public void createOrder() throws IOException {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) {
            showUserNotFoundNotification();
            return;
        }
        Order order = new Order();
        order.setTimestamp(new Timestamp(dateTime(date, time).getTime()));
        order.setGuestCount(guestCount);
        order.setUser(user);
        iOrderService.save(order);

        Set<Order_dish> order_dishes = new HashSet<>();
        for (Map.Entry<Dish, Integer> entry : createMap(dishes).entrySet()) {
            order_dishes.add(new Order_dish(order.getId(), entry.getKey().getId(), order, entry.getKey(), entry.getValue()));
        }
        order.setOrder_dishes(order_dishes);
        iOrderService.save(order);


        dishes = new ArrayList<>();

        goToMenuPage();
    }

    private Map<Dish, Integer> createMap(List<Dish> dishes) {
        Map<Dish, Integer> map = new HashMap<>();

        for (Dish dish : dishes) {
            Integer i = 1;
            if (map.containsKey(dish)) {
                i = map.get(dish) + 1;
            }
            map.put(dish, i);
        }

        return map;
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
