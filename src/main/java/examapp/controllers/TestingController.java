package examapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class TestingController {
    @PostMapping("/testingPage")
    public String doTest(){

        return "/testing/testingPage";
    }
}
