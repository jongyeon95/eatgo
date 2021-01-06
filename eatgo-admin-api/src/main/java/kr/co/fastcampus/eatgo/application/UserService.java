package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();

        return users;
    }

    public User addUser(String email, String name) {
        User user=userRepository.save(User.builder().name(name).email(email).level(1L).build());
        return user;
    }

    public User updateUser(Long id, String email, String name, Long level) {
        User user=userRepository.findById(id).orElse(null);
        user.setEmail(email);
        user.setLevel(level);
        user.setName(name);
        return user;
    }

    public User deactiveUser(long id) {
        User user=userRepository.findById(id).orElse(null);
        user.deactivate();
        return user;
    }
}
