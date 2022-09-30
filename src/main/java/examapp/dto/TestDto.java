package examapp.dto;

import examapp.models.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDto {
    private List<Question> questions;
    private int rate;
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
    public int getRate(){
        return rate;
    }
}
