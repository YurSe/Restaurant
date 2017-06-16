package by.restaurant.controller;

import by.restaurant.model.Feedback;
import by.restaurant.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Pavel on 15.06.2017.
 */
@Controller
@Scope("request")
public class FeedbackContr implements Serializable{

    @Autowired
    private FeedbackService feedbackService;

    private String name;

    private String phoneNumber;

    private String description;


    public FeedbackContr() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void goToMenuPage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().put("savedMessageWithSuccess", "true");
        context.redirect(context.getRequestContextPath() + "/pages/menu.xhtml");
    }

    public void showSentMessageNotification() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String labelCreatedWithSuccess = (String) ec.getSessionMap().get("savedMessageWithSuccess");
        if (labelCreatedWithSuccess != null && labelCreatedWithSuccess.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messageSaved", new FacesMessage("Successful", "Thank you very much, message sent successfully." +
                    "We will contact you soon!"));
            ec.getSessionMap().remove("savedMessageWithSuccess");
        }
    }

    public void saveMessage() throws IOException {
        Feedback feedback = new Feedback();
        feedback.setDescription(description);
        feedback.setName(name);
        feedback.setPhoneNumber(phoneNumber);
        feedbackService.save(feedback);
        goToMenuPage();
    }
}
