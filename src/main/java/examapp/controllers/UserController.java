package examapp.controllers;

import examapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private final QuestionService questionService;

    public UserController( QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/user/home")
    public String homePage(){
        return "/user/home";
    }
    @GetMapping("/user/testingPage")
    public String testingPage(Model model){
        model.addAttribute("questions", questionService.findAll());
        return "/testing/testingPage";
    }

}
