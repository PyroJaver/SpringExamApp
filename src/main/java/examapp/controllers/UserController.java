package examapp.controllers;

import examapp.models.User;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
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

    @GetMapping ("/userList/{id}")
    public String editUserPage(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userDetailService.findById(id));
        return "user/editUser";
    }

    @PatchMapping("/userList/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable("id") int id){
        userDetailService.update(id, user);
        return "/user/home";
    }
    @GetMapping("/userList")
    public String openUsersList(Model model){
        model.addAttribute("users", userDetailService.loadAllUsers() ) ;
        return "user/userList";
    }
    @DeleteMapping ("/userList/{id}")
    public String deleteUserByAdmin(@PathVariable("id") int id){
        userDetailService.deleteUserById(id);
        return "redirect:/user/userList";
    }
}
