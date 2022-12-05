package examapp.serviceTests;

import examapp.models.Question;
import examapp.repositories.QuestionRepo;
import examapp.services.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    @MockBean
    private QuestionRepo questionRepo;

    @Test
    public void saveQuestionTest(){
        Question question = new Question();
        question.setQuestionText("testQuestionText");
        question.setAnswer("testAnswer");
        questionService.saveQuestion(question);
        verify(questionRepo, Mockito.times(1)).save(question);
    }
    //Этот тест падает, если не закомментировать аннотацию @Component у StartupData
    @Test
    public void deleteQuestionByIdTest(){
        Question question = new Question();
        question.setId(1);
        questionRepo.save(question);
        when(questionRepo.findById(question.getId())).thenReturn(Optional.of(question));
        questionService.deleteQuestionById(1);
        verify(questionRepo).deleteById(question.getId());
    }
    @Test
    public void deleteQuestionByIdFailTest(){
        Question question = new Question();
        question.setId(123);
        when(questionRepo.findById(anyInt())).thenReturn(Optional.empty());
        questionService.deleteQuestionById(question.getId());
    }
    @Test
    public void updateQuestionTest(){
        Question question = new Question();
        question.setId(1);
        question.setQuestionText("testQuestionText");
        question.setAnswer("testAnswer");

        Question question2 = new Question();
        question2.setQuestionText("anotherTestQuestionText");
        question2.setAnswer("anotherTestAnswer");

        when(questionRepo.findById(question.getId())).thenReturn(Optional.of(question));
        questionService.updateQuestion(question.getId(), question2);
        Mockito.verify(questionRepo, Mockito.times(1)).findById(question.getId());
        Mockito.verify(questionRepo, Mockito.times(1)).save(question2);
    }
    @Test(expected = RuntimeException.class)
    public void updateQuestionFailTest(){
        Question question = new Question();
        question.setId(1);
        question.setQuestionText("testQuestionText");
        question.setAnswer("testAnswer");

        Question question2 = new Question();
        question2.setQuestionText("anotherTestQuestionText");
        question2.setAnswer("anotherTestAnswer");
        question2.setId(2);
        when(questionRepo.findById(question.getId())).thenReturn(Optional.ofNullable(null));
        questionService.updateQuestion(question.getId(), question2);
    }
    @Test
    public void findQuestionByIdTest(){
        Question question = new Question();
        question.setId(1);
        question.setQuestionText("testQuestionText");
        when(questionRepo.findById(question.getId())).thenReturn(Optional.of(question));
        Question expectedQuestion = questionService.findById(question.getId());
        Assert.assertEquals(expectedQuestion, question);
        verify(questionRepo, Mockito.times(1)).findById(question.getId());
    }
}
