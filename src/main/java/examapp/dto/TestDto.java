package examapp.dto;

import examapp.models.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDto {

    private List<Question> questions;
    private double rate;
    private double maxRate;
    private double percentageOfCompletion;

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


    public double getPercentageOfCompletion(){

        return percentageOfCompletion;
    }
    public double getMaxRate(){
        return maxRate;
    }
    public double getRate(){
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setMaxRate(double maxRate) {
        this.maxRate = maxRate;
    }

    public void setPercentageOfCompletion(double percentageOfCompletion) {
        this.percentageOfCompletion = percentageOfCompletion;
    }
}
