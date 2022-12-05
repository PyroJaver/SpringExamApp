package examapp.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import examapp.models.User;
import examapp.services.UserDetailService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/hibernate-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("testUsername")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void homePageTest() throws Exception {
        this.mockMvc.perform(get("/user/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<button class=\"adminPage\">")));
    }

    @Test
    public void userListTest() throws Exception{
        this.mockMvc.perform(get("/user/userList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id=\"user\"]").nodeCount(2));
    }


    @Test
    public void editUserPageTest() throws Exception {
          this.mockMvc.perform(get("/user/editUser", 2))
        .andDo(print())
        .andExpect(status().isOk())
                  //следующие проверки нужны для того, заполнились ли формы полями переданного пользователя
        .andExpect(content().string(containsString
                ("<input type=\"text\" id=\"username\" name=\"username\" value=\"testUsername\"/>")))
        .andExpect(content().string(containsString
                ("<input type=\"text\" id=\"name\" name=\"name\" value=\"testName\"/>")))
        .andExpect(content().string(containsString
                ("<input type=\"text\" id=\"surname\" name=\"surname\" value=\"testSurname\"/>")))
        .andExpect(content().string(containsString
                ("<input type=\"text\" id=\"yearOfBirth\" name=\"yearOfBirth\" value=\"2000\"/>")));
    }

    @Test
    public void deleteUserTest() throws Exception {
        Assert.assertEquals(userDetailService.loadAllUsers().size(), 2);
        User testUser = userDetailService.findById(1);
        User testUser2 = userDetailService.findById(2);
    this.mockMvc.perform(delete("/user/userList/{id}", testUser2.getId()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/userList"));
        Assert.assertEquals(userDetailService.loadAllUsers().size(),1);
        Assert.assertEquals(userDetailService.findById(1).getUsername(), testUser.getUsername());
    }

    @Test
    @Ignore
    public void editUserTest() throws Exception {
        User testUser2 = new User("username", "name", "name", "name", 2000);
        this.mockMvc.perform(patch("/user/userList/{id}",  2 )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser2))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/userList"));

    }
}
