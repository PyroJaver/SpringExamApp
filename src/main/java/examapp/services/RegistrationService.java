package examapp.services;

import examapp.models.User;
import examapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
   @Autowired
    public RegistrationService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
       this.passwordEncoder = passwordEncoder;
   }
    @Transactional
    public void register(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRole("ROLE_USER");
       userRepo.save(user);
    }
}
