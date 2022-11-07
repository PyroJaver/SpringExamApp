package examapp.controllers;

import examapp.dto.TestDto;
import examapp.services.TestingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class TestingController {
    private final TestingService testingService;

    private final TestDto testDto;


    public TestingController(TestingService testingService, TestDto testDto) {
        this.testingService = testingService;
        this.testDto = testDto;
    }

    @GetMapping("/testingPage")
    public String testingPage(Model model) {
        testDto.setQuestions(testingService.setNewTestQuestions());
        model.addAttribute("test", testDto);
        return "/testing/testingPage";
    }

    @PostMapping("/user/testingPage")
    public String saveAnsweredQuestions(@ModelAttribute("test") TestDto testDto) {
        testingService.setAnsweredQuestions(testDto.getQuestions());
        return "redirect:/user/testResults";
    }

    @GetMapping("/user/testResults")
    public String showTestResults(Model model) {
        testingService.calculateTestingResults(testDto);
        model.addAttribute("testResult", testDto);
        return "testing/testResults";
    }
}
