package by.restaurant.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Pavel on 17.04.2017.
 */

@FacesValidator
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

            String password = value.toString();
            UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
                    .get("pwd2");
            Object confirmPassword = uiInputConfirmPassword.getValue();
            if (password == null || password.isEmpty() || confirmPassword == null) {
                return;
            }
            if (!password.equals(confirmPassword)) {
                uiInputConfirmPassword.setValid(false);
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",
                        "'Passwords' don't match. Try again."));
            }
        }
    }

