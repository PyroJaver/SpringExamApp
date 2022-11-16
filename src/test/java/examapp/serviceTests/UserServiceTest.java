package examapp.serviceTests;


import examapp.models.User;
import examapp.repositories.UserRepo;
import examapp.services.UserDetailService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
  @Autowired
    UserDetailService userDetailService;
  @MockBean
    private UserRepo userRepo;
  @MockBean
    private PasswordEncoder passwordEncoder;

  @Test
    public void registerUserTest(){
      User user = new User();
      userDetailService.registerUser(user);
      Assert.assertEquals(user.getRole(), "ROLE_USER");
      verify(userRepo, Mockito.times(1)).save(user);
  }

  //Этот тест падает, если не закомментировать аннотацию @Component у StartupData
  @Test
  @Ignore
  public void registerUserFailTest(){
    User user = new User();
    user.setUsername("testUser");
    Mockito.doReturn(new User())
            .when(userRepo)
            .findByUsername("testUser");

    Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    Mockito.verify(userRepo, Mockito.times(1)).findByUsername(ArgumentMatchers.any(String.class));
  }
  @Test
  public void deleteUserByIdTest(){
    User user = new User();
    user.setId(1);
    userRepo.save(user);
    when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
    userDetailService.deleteUserById(1);
    verify(userRepo).deleteById(user.getId());
  }
  @Test
  public void deleteUserByIdFailTest(){
    User user = new User();
    user.setId(123);
    when(userRepo.findById(anyInt())).thenReturn(Optional.empty());
    userDetailService.deleteUserById(user.getId());
  }
  @Test
  public void updateUserTest(){
    User user = new User();
    user.setId(1);
    user.setUsername("testUsername");

    User user2 = new User();
    user2.setUsername("anotherTestUsername");
    user2.setId(2);

    when(userRepo.findById(user.getId())).thenReturn(Optional.of(user2));
    userDetailService.update(user.getId(), user2);
    Mockito.verify(userRepo, Mockito.times(1)).findById(user.getId());
    Mockito.verify(userRepo, Mockito.times(1)).save(user2);
  }
  @Test(expected = RuntimeException.class)
  public void updateUserFailTest(){
    User user = new User();
    user.setId(123);
    user.setUsername("testUsername");
    User user2 = new User();
    user2.setUsername("anotherTestUsername");
    user2.setId(122);
    when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(null));
    userDetailService.update(user.getId(), user2);
  }
  @Test
  public void findUserByIdTest(){
    User user = new User();
    user.setId(1);
    user.setUsername("testUsername");
    when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
    User expectedUser = userDetailService.findById(user.getId());
    Assert.assertEquals(expectedUser, user);
    verify(userRepo, Mockito.times(1)).findById(user.getId());
  }
}
