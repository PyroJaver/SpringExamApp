package examapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/adminPage")
    public String adminPanel(){
        return "/admin/adminPage";
    }

}
