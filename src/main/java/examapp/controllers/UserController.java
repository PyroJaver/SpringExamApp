package examapp.controllers;

import examapp.models.User;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String homePage(Model model){
        model.addAttribute("currentUser", userDetailService.getCurrentUser() );
        return "/user/home";
    }

    @GetMapping ("/editUser")
    public String editUserPage(Model model){
        model.addAttribute("user", userDetailService.getCurrentUser());
        return "user/editUser";
    }

    @PatchMapping("/editUser/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable("id") int id){
        userDetailService.update(id, user);
        return "/user/editUser";
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
