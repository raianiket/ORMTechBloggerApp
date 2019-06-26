package techblogapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techblogapp.model.User;
import techblogapp.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User loginUser(User user){
        return userRepository.checkUser(user);
    }

    public void registerUser(User user) {
        userRepository.registerUser(user);
    }
}
