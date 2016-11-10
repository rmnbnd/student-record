package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}
