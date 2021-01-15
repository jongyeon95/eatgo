package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {


    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService=new UserService(userRepository,passwordEncoder);
    }


    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotExistedEmail(){
        String email = "x@example.com";
        String password = "test";
        given(userRepository.findByEmail(email)).willReturn(Optional.empty());
        User user = userService.authenticate(email,password);
        assertThat(user.getEmail(),is(email));
    }

    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword(){
        String email = "tester@example.com";
        String password = "x";
        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        userService.authenticate(email,password);
    }


    @Test
    public void authenticateWithValidAttributes(){
        String email = "tester@example.com";
        String password = "test";
        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(),any())).willReturn(true);
        User user = userService.authenticate(email,password);

        assertThat(user.getEmail(),is(email));
    }



}