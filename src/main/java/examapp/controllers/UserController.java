package examapp.controllers;

import examapp.models.Question;
import examapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {
    @Value("${questions.sizeOfTest}") int sizeOfTest;
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
        List<Question> questions = questionService.getSomeQuestions();
        model.addAttribute("questions", questions);
        return "/testing/testingPage";
    }


}
