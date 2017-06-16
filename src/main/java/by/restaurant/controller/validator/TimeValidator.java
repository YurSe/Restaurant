package by.restaurant.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pavel on 16.06.2017.
 */
@FacesValidator
public class TimeValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }
        if(value instanceof Date) {
            DateFormat formatter = new SimpleDateFormat("HH/mm/ss");
            Date today = new Date();
            Date todayWithZeroDate = null;
            try {
                todayWithZeroDate = formatter.parse(formatter.format(today));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = (Date)(value);
            if(date.before(todayWithZeroDate) ) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error","Can't select the last time."));
            }
        }
    }
}
