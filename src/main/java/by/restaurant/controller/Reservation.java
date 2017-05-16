package by.restaurant.controller;

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

/**
 * Created by Pavel on 15.05.2017.
 */
@Component
@Scope("request")
public class Reservation implements Serializable{

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UserRepository userRepository;

    private Date date;

    private Date time;

    private Integer guestCount;

    public Reservation() {
    }

    private Date dateTime(Date date, Date time) {
        return new Date(
                date.getYear(), date.getMonth(), date.getDay(),
                time.getHours(), time.getMinutes(), time.getSeconds()
        );
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
