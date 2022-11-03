package examapp.dto;

import examapp.models.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDto {

    private List<Question> questions;
    private double rate;

    public TestDto() {
    }

    public TestDto(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void incrementRate(){
        rate++;
    }

    public double getPercentageOfCompletion(){
        double maxRate = questions.size();
        return 100*rate/maxRate;
    }
    public double getMaxRate(){
        double maxRate = questions.size();
        return maxRate;
    }
    public double getRate(){
        return rate;
    }
}
