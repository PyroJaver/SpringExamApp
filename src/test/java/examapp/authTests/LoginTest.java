package examapp.authTests;

import examapp.controllers.AuthController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthController authController;
    @Test
    public void contextLoadingTest() throws Exception{
    this.mockMvc.perform(get("/auth/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("label for=\"username\"")));
    }
    @Test
    public void redirectionToLoginPageTest() throws Exception{
        this.mockMvc.perform(get("/user/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/auth/login"));
    }
    @Test
    public void correctAuthenticationTest() throws Exception{
    this.mockMvc.perform(formLogin("http://localhost/process_login").user("testUsername")
                    .password("adminadmin"))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/home"));
    }
    @Test
    public void incorrectAuthenticationTest() throws Exception{
        this.mockMvc.perform(formLogin("http://localhost/process_login").user("incorrectUser")
                        .password("incorrectPassword"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login?error"));
    }
    @WithUserDetails("testUsername")
    @Test
    public void homePageTest() throws Exception{
        this.mockMvc.perform(get("/user/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<button class=\"adminPage\">")));
    }
}
