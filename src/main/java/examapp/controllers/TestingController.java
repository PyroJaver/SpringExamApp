package examapp.controllers;

import examapp.dto.TestDto;
import examapp.models.Question;
import examapp.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/user")
public class TestingController {
    private final QuestionService questionService;
    private final TestDto testDto;

    public TestingController(QuestionService questionService,  TestDto testDto) {
        this.questionService = questionService;
        this.testDto = testDto;
    }

    @GetMapping("/testingPage")
    public String testingPage(Model model){
        testDto.setQuestions(questionService.getNewTestQuestions());
        model.addAttribute("test", testDto);
        return "/testing/testingPage";
    }

    @PostMapping("/testingPage")
    public String saveAnsweredQuestions(@ModelAttribute("test") TestDto testDto ){
        questionService.setAnsweredQuestions(testDto.getQuestions());

        return "redirect:/user/testResults";
    }
    @GetMapping("/testResults")
    public String showTestResults(@ModelAttribute("testResult") TestDto testDto){
        List<Question> testQuestions = questionService.getTestQuestions();
        List<Question> answeredQuestions = questionService.getAnsweredQuestions();

        //сортировка необходима, чтобы for each и итератор шли по спискам вопросов синхронно
        Collections.sort(answeredQuestions);
        Collections.sort(testQuestions);

        //ответ считается правильным, если поле answer для двух вопросов полностью совпадает.
        //TODO: реализовать игнорирование регистра букв и одной опечатки в строке
        for ( Question question: answeredQuestions){
            if(question.getAnswer()==testQuestions.iterator().next().getAnswer()){
             testDto.incrementRate();
        }
        }
        return "testing/testResults";
    }
}
