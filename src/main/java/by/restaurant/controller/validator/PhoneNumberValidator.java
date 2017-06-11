package by.restaurant.controller.validator;

import by.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 17.04.2017.
 */
@Component()
@Scope("request")
public class PhoneNumberValidator implements Validator{

    @Autowired
    private UserRepository userRepository;

    private Pattern pattern;

    private static final String NAME_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    public PhoneNumberValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }

        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error","Phone number is not valid. Example: +375 XX xxxxxxx"));
        }

        if(userRepository.findByPhoneNumber(value.toString()) != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "User with this number phone already exists."));
        }
    }
}
