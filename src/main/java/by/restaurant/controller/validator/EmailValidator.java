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
 * Created by Pavel on 16.04.2017.
 */
@Component()
@Scope("request")
public class EmailValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }
        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error","Email is not valid. Example: example@gmail.com"));
        }
        if(userRepository.findByEmail(value.toString()) != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "User with this email already exists."));
        }
    }
}
