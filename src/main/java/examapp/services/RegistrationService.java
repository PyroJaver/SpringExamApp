package examapp.services;

import examapp.models.User;
import examapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final UserRepo userRepo;
   @Autowired
    public RegistrationService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    public void register(User user){
       userRepo.save(user);
    }
}
