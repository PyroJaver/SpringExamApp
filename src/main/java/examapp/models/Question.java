package examapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name ="questions")
public class Question implements Comparable<Question>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name ="questionText")
    @Size(min = 2, max = 1000)
    @NotEmpty
    private String questionText;

    @Column(name ="answer")
    @Size(min = 2, max = 1000)
    @NotEmpty
    private String answer;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionText.equals(question.questionText) && answer.equals(question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public int compareTo(Question question) {
        if(this.id>question.getId()) {
            return 1;
        } else
        if(this.id< question.getId()) {
            return -1;
        } else
            return 0;
    }
}
