package examapp.controllers;

import examapp.dto.TestDto;
import examapp.models.Question;
import examapp.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class TestingController {
    private final QuestionService questionService;
    private final TestDto testDto;

    public TestingController(QuestionService questionService, TestDto testDto) {
        this.questionService = questionService;
        this.testDto = testDto;
    }

    @GetMapping("/testingPage")
    public String testingPage(Model model) {
        testDto.setQuestions(questionService.getNewTestQuestions());
        model.addAttribute("test", testDto);
        return "/testing/testingPage";
    }

    @PostMapping("/user/testingPage")
    public String saveAnsweredQuestions(@ModelAttribute("test") TestDto testDto) {
        questionService.setAnsweredQuestions(testDto.getQuestions());

        return "redirect:/user/testResults";
    }

    @GetMapping("/user/testResults")
    public String showTestResults(@ModelAttribute("testResult") TestDto testDto) {
        ArrayList<Question> testQuestions = (ArrayList<Question>) questionService.getTestQuestions();
        ArrayList<Question> answeredQuestions = (ArrayList<Question>) questionService.getAnsweredQuestions();

        //хоть сами вопросы не будут использованы в представлении, они нужны для вычисления процента выполнения теста
        testDto.setQuestions(questionService.getTestQuestions());

        //сортировка необходима, чтобы идти по исходным и отвеченным вопросам синхронно
        Collections.sort(answeredQuestions);
        Collections.sort(testQuestions);


        for (int i = 0; i < testQuestions.size(); i++) {
            //ответ считается правильным, если поле answer для двух вопросов полностью совпадает
            //регистр букв игнорируется
            if (Objects.equals(testQuestions.get(i).getAnswer().toLowerCase(),
                    answeredQuestions.get(i).getAnswer().toLowerCase())) {
                testDto.incrementRate();
            }

        }
        return "testing/testResults";
    }
}
