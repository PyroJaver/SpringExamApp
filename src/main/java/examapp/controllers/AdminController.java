package examapp.controllers;

import examapp.models.User;
import examapp.services.QuestionService;
import examapp.services.UserDetailService;
import examapp.util.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//TODO: этот контроллер чересчур разросся,нужен рефакторинг. Выделить контроллеры пользователей и вопросов.
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final QuestionValidator questionValidator;
    private final QuestionService questionService;
    private final UserDetailService userDetailService;

    @Autowired
    public AdminController(QuestionValidator questionValidator, QuestionService questionService,
                           UserDetailService userDetailService) {
        this.questionValidator = questionValidator;
        this.questionService = questionService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/adminPage")
    public String adminPanel(){
        return "/admin/adminPage";
    }
/*
    @GetMapping("/newQuestion")
    public String newQuestionPage(@ModelAttribute("question") Question question){
        return "/admin/newQuestion";
    } */

 /*   @PostMapping("/newQuestion")
    public String addNewQuestion(@ModelAttribute("question") @Valid Question question,
                                BindingResult bindingResult){
        questionValidator.validate(question, bindingResult);
        if (bindingResult.hasErrors()){
            return "/admin/newQuestion";
        }
        questionService.saveQuestion(question);
        return "/admin/newQuestion";
    }  */

    /*
    @GetMapping("/questionList")
    public String openQuestionsList(Model model){
        model.addAttribute("questions", questionService.getAllQuestions()) ;
        return "/admin/questionList";
    }
    @DeleteMapping ("/questionList/{id}")
    public String deleteQuestion(@PathVariable("id") int id){
        questionService.deleteQuestionById(id);
        return "redirect:/home";
    }
    @GetMapping ("/questionList/{id}")
    public String editQuestionPage(Model model, @PathVariable("id") int id){
        model.addAttribute("question", questionService.findById(id));
        return "/admin/editQuestion";
    }

    @PatchMapping("/questionList/{id}")
    public String editQuestion(@ModelAttribute Question question, @PathVariable("id") int id){
    questionService.update(id, question);
    return "/user/home";
    }
*/




    /*
    @GetMapping ("/userList/{id}")
    public String editUserPage(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userDetailService.findById(id));
        return "users/editUser";
    }

    @PatchMapping("/userList/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable("id") int id){
        userDetailService.update(id, user);
        return "/user/home";
    }
    @GetMapping("/userList")
    public String openUsersList(Model model){
        model.addAttribute("users", userDetailService.loadAllUsers() ) ;
        return "users/userList";
    }
    @DeleteMapping ("/userList/{id}")
    public String deleteUserByAdmin(@PathVariable("id") int id){
        userDetailService.deleteUserById(id);
        return "redirect:/home";
    }*/
}
