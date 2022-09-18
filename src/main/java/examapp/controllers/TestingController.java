package examapp.controllers;

import examapp.services.QuestionService;
import examapp.util.QuestionValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class TestingController {
    private final QuestionService service;
    private final QuestionValidator validator;

    public TestingController(QuestionService service, QuestionValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @PostMapping("/testingPage")
    public String doTest(Model model, BindingResult bindingResult){
//model.addAttribute("questions", service.getSomeQuestions() );
      // validator.validate(question, bindingResult);
       if(bindingResult.hasErrors()){
           return "/testing/testingPage";
       }
        return "/testing/testingPage";
    }
}
