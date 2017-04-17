package by.restaurant.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 17.04.2017.
 */
@FacesValidator
public class PhoneNumberValidator implements Validator{


    private Pattern pattern;

    private static final String NAME_PATTERN = "\\+375(\\d){9}";

    public PhoneNumberValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }

        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error","Phone number is not valid. Example: +375XXxxxxxxx"));
        }
    }
}
