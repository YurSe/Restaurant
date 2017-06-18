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
@SuppressWarnings("deprecation")
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

    private Map<Dish, String> dishes = new HashMap<>();

    public Reservation() {
    }

    private boolean isDateValid() {
        return dateTime(date, time).after(new Date());
    }

    public Map<Dish, String> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Dish, String> dishes) {
        this.dishes = dishes;
    }

    public int getHoursFromTime() {
        if (date != null && date.after(new Date())) {
            return MIN_HOUR;
        }
        minDate = new Date();
        return minDate.getHours();
    }

    public List<Dish> getDishList() {
        return new ArrayList<>(dishes.keySet());
    }

    public void AddDish(Dish dish) {
        if (!dishes.containsKey(dish)) {
            dishes.put(dish, "1");
        } else {
            Integer v = Integer.parseInt(dishes.get(dish)) + 1;
            dishes.remove(dish);
            dishes.put(dish, v.toString());
        }
    }

    public void RemoveDish(Dish dish) {
        dishes.remove(dish);
    }

    public boolean IsDishesEmpty() {
        return dishes.isEmpty();
    }

    public void Clear() {
        date = null;
        time = null;
        guestCount = null;
        dishes = new HashMap<>();

    }

    public Double getFullPrice() {
        double fullPrice = 0;
        for (Map.Entry<Dish, String> entry : dishes.entrySet())
            for (int i = 0; i < Integer.parseInt(entry.getValue()); i++)
                fullPrice += entry.getKey().getPrice();
        return Math.round(fullPrice * 100.0) / 100.0;
    }


    private Date dateTime(Date date, Date time) {
        return new Date(
                date.getYear(), date.getMonth(), date.getDate(),
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

    private void showTimeIsUpNotification() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messageTimeIsUp", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can't select the last date."));
    }

    private void goToMenuPage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().put("createdWithSuccess", "true");
        context.redirect(context.getRequestContextPath() + "/pages/menu.xhtml");
    }

    public void createOrder() throws IOException {
        if (date == null && time == null && guestCount == null) {
            date = new Date();
            time = new Date();
            guestCount = 1;
        } else if (!isDateValid()) {
            showTimeIsUpNotification();
            return;

        }
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
        //Save order to get id for it.
        order = iOrderService.getById(order.getId());

        Set<Order_dish> order_dishes = new HashSet<>();
        for (Map.Entry<Dish, String> entry : dishes.entrySet()) {
            order_dishes.add(new Order_dish(order.getId(), entry.getKey().getId(), order, entry.getKey(),
                    Integer.parseInt(entry.getValue())));
        }
        order.setOrder_dishes(order_dishes);
        iOrderService.save(order);

        Clear();
        //goToMenuPage();
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
