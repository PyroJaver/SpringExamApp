package examapp.controllerTests;


import examapp.services.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/hibernate-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest
@Sql(value = {"/create-user-before.sql", "/create-questions-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-questions-after.sql","/create-user-after.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("testUsername")
public class QuestionControllerTest {
    @Autowired
    QuestionService questionService;
    @Autowired
    MockMvc mockMvc;

    @Test
   public void  newQuestionPageTest() throws Exception {
        this.mockMvc.perform(get("/questions/newQuestion"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id=\"questionText\"]").exists());
    }
    @Test
    public void questionListTest() throws Exception{
        this.mockMvc.perform(get("/questions/questionList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id=\"question\"]").nodeCount(3));
    }
    @Test
    public void deleteQuestionTest() throws Exception {
        Assert.assertEquals(questionService.getAllQuestions().size(), 3);
        this.mockMvc.perform(delete("/questions/questionList/{id}", 2))
                .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/questions/questionList"));
        Assert.assertEquals(questionService.getAllQuestions().size(),2);
    }
    @Test
    public void editQuestionPageTest() throws Exception {
        this.mockMvc.perform(get("/questions/questionList/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                //следующие проверки нужны для того, заполнились ли формы полями переданного вопроса
                .andExpect(content().string(containsString
                        ("<input type=\"text\" id=\"questionText\" " +
                                "name=\"questionText\" value=\"testQuestionText\"/>")))
                .andExpect(content().string(containsString
                        ("<input type=\"text\" id=\"answer\" name=\"answer\" value=\"testAnswer\"/>")));
    }
}
