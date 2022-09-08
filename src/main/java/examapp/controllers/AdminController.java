package examapp.controllers;

import examapp.models.Question;
import examapp.models.User;
import examapp.services.QuestionService;
import examapp.util.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final QuestionValidator questionValidator;
    private final QuestionService questionService;

    @Autowired
    public AdminController(QuestionValidator questionValidator, QuestionService questionService) {
        this.questionValidator = questionValidator;
        this.questionService = questionService;
    }

    @GetMapping("/adminPage")
    public String adminPanel(){
        return "/admin/adminPage";
    }

    @GetMapping("/newQuestion")
    public String newQuestionPage(@ModelAttribute("question") Question question){
        return "admin/newQuestion";
    }

    @PostMapping("/newQuestion")
    public String addNewQuestion(@ModelAttribute("question") @Valid Question question,
                                BindingResult bindingResult){
        questionValidator.validate(question, bindingResult);
        if (bindingResult.hasErrors()){
            return "/admin/newQuestion";
        }
        questionService.saveQuestion(question);
        return "/admin/newQuestion";
    }

}
