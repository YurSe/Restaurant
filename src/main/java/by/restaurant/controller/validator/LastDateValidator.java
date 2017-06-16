package by.restaurant.controller.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pavel on 16.06.2017.
 */
@Component()
@Scope("request")
public class LastDateValidator implements Validator{

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException{
        if(value == null) {
            return;
        }
        if(value instanceof Date) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();
            Date todayWithZeroTime = null;
            try {
                todayWithZeroTime = formatter.parse(formatter.format(today));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = (Date) value;
            if(date.before(todayWithZeroTime) ) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error","Can't select the last date."));
            }
        }
    }
}
