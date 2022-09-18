package examapp.util;

import examapp.models.Question;
import examapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TestingLogic {
    @Value("${questions.sizeOfTest}")
     int sizeOfTest;

    private double maxRate = sizeOfTest;
    private double rate;
    @Autowired
    private final QuestionService questionService;

    public TestingLogic(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void questionAnswerProcessing(Question answeredQuestion){
        Question originQuestion = questionService.findById(answeredQuestion.getId());
        if(Objects.equals(originQuestion.getAnswer(), answeredQuestion.getAnswer())){
            rate++;
        }
    }

     public double endTest(){
        return rate/maxRate;
    }
}
