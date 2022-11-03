package examapp.controllers;

import examapp.models.User;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/home")
    public String homePage(){
        return "/user/home";
    }

    @DeleteMapping("/{id}")
    public String deleteUserByUser(@ModelAttribute User user){
        userDetailService.deleteUserByEntity(user);
        return "redirect:/home";
    }
}
