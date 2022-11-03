package examapp.services;

import examapp.models.Question;
import examapp.models.User;
import examapp.repositories.UserRepo;
import examapp.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //FIXME:Использование этого метода для загрузки пользователя при валидации приводит к невозможности регистрации
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return new UserDetail(user.get());
    }
    public Optional<User> getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public List<User> loadAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return allUsers;
    }

    @Transactional
    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //условный оператор нужен для корректной работы StartupData
        if(user.getRole()==null) {
            user.setRole("ROLE_USER");
        }
        userRepo.save(user);
    }

    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void deleteUserById (int id){
        userRepo.deleteById(id);
    }

    @Transactional
    public void deleteUserByEntity(User user){ userRepo.delete(user);}



    public void update(int id, User updatedUser){
        User userToBeUpdated = findById(id);

        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setYearOfBirth(updatedUser.getYearOfBirth());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setRole(updatedUser.getRole());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setName(updatedUser.getName());

        userRepo.save(userToBeUpdated);
    }

    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent() ){
            return user.get();
        } else
        return new User();
    }

}
