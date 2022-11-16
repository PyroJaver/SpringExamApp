package examapp.controllers;

import examapp.models.User;
import examapp.services.UserDetailService;
import examapp.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final UserDetailService userDetailService;
    @Autowired
    public AuthController(UserValidator userValidator, UserDetailService userDetailService) {
        this.userValidator = userValidator;
        this.userDetailService = userDetailService;
    }


    @GetMapping("/login")
    public String loginPage(){
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user")User user){

         return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult){
    userValidator.validate(user, bindingResult);

    if(bindingResult.hasErrors()){
        return "/auth/registration";
    }
    userDetailService.registerUser(user);
    return "redirect:/auth/login";
    }
}
