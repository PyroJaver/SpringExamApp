package examapp.util;

import examapp.models.User;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
     User user = (User)target;
    try{
        userDetailService.loadUserByUsername(user.getUsername());
    }catch (UsernameNotFoundException ignored){
        return; //All is OK, user not found
    }
    errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }
}
