package examapp.util;

import examapp.models.User;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidator implements Validator {

    private final UserDetailService userDetailService;

    @Autowired
    public UserValidator(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        //Username and password can't me empty or contain whitespace
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.not_empty");

        // Username must have from 4 characters to 32
        if (user.getUsername().length() < 4) {
            errors.rejectValue("username", "registration.error.username.less_4");
        }

        if (user.getUsername().length() > 32) {
            errors.rejectValue("username", "registration.error.username.over_32");
        }

        //Username can't be duplicated
        if (userDetailService.getUserByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "registration.error.duplicated.username");
        }

        //Password must have at least 8 characters and max 32
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "registration.error.password.less_8");
        }

        if (user.getPassword().length() > 32){
            errors.rejectValue("password", "registration.error.password.over_32");
        }


    }
}
