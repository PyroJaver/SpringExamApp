package examapp.controllers;

import examapp.models.Question;
import examapp.services.QuestionService;
import examapp.util.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionValidator questionValidator;

    @Autowired
    public QuestionController(QuestionService questionService, QuestionValidator questionValidator) {
        this.questionService = questionService;
        this.questionValidator = questionValidator;
    }


    @GetMapping("/newQuestion")
    public String newQuestionPage(@ModelAttribute("question") Question question){
        return "questions/newQuestion";
    }

    @PostMapping("/newQuestion")
    public String addNewQuestion(@ModelAttribute("question") @Valid Question question,
                                 BindingResult bindingResult){
        questionValidator.validate(question, bindingResult);
        if (bindingResult.hasErrors()){
            return "questions/newQuestion";
        }
        questionService.saveQuestion(question);
        return "questions/newQuestion";
    }
    @GetMapping("/questionList")
    public String openQuestionsList(Model model){
        model.addAttribute("questions", questionService.getAllQuestions()) ;
        return "questions/questionList";
    }
    @DeleteMapping("/questionList/{id}")
    public String deleteQuestion(@PathVariable("id") int id){
        questionService.deleteQuestionById(id);
        return "redirect:/questions/questionList";
    }
    @GetMapping ("/questionList/{id}")
    public String editQuestionPage(Model model, @PathVariable("id") int id){
        model.addAttribute("question", questionService.findById(id));
        return "questions/editQuestion";
    }

    @PatchMapping("/questionList/{id}")
    public String editQuestion(@ModelAttribute Question question, @PathVariable("id") int id){
        questionService.updateQuestion(id, question);
        return "/user/home";
    }
}
