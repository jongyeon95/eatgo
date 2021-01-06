package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.hibernate.annotations.ManyToAny;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService=new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> MockUsers= new ArrayList<>();
        MockUsers.add(User.builder().name("tester").email("tester@example.com").level(3L).build());
        given(userRepository.findAll()).willReturn(MockUsers);
        List<User> users= userService.getUsers();
        assertThat(users.get(0).getName(),is("tester"));
    }

    @Test
    public void addUser(){
        String name="tester";
        String email="tester@example.com";
        User MockUser= User.builder().name(name).email(email).level(3L).build();
        given(userRepository.save(any())).willReturn(MockUser);
        User user= userService.addUser(email,name);
        assertThat(user.getName(),is(name));

    }

    @Test
    public void updateUser(){
        Long id =1004L;
        String name="Superman";
        String email="tester@example.com";
        Long level=100L;

        User mockUser=User.builder().id(id).email(email).name("tester").level(level).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
        User user = userService.updateUser(id,email,name,level);
        verify(userRepository).findById(eq(id));
        assertThat(user.getName(),is("Superman"));
        assertThat(user.isAdmin(),is(true));

    }

    @Test
    public void deactiveUser(){
        Long id =1004L;
        String name="tester";
        String email="tester@example.com";
        Long level=100L;

        User mockUser=User.builder().id(id).email(email).name(name).level(level).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
        User user = userService.deactiveUser(1004L);
        verify(userRepository).findById(1004L);
        assertThat(user.isAdmin(),is(false));
        assertThat(user.isActive(),is(false));
    }

}