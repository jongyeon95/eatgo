package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;

    }

    public User authenticate(String email,String password) {
        User user=userRepository.findByEmail(email).orElseThrow(()->new EmailNotExistedException(email));

        passwordEncoder.matches(password,user.getPassword());
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordWrongException();
        }
        return  user;
    }
}
