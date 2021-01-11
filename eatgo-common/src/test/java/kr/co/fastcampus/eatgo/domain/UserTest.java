package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void creation(){
        User user=User.builder()
                .name("테스터")
                .email("tester@example.com")
                .level(10L)
                .build();

        assertThat(user.getName(),is("테스터"));
        assertThat(user.isAdmin(),is(true));
        assertThat(user.isActive(),is(true));
        user.deactivate();
        assertThat(user.isActive(),is(false));
    }

    @Test
    public void accessTokenWithPassword(){
        User user= User.builder().password("ACCESSTOKEN").build();
        assertThat(user.getAccessToken(),is("ACCESSTOKE"));
    }


    @Test
    public void accessTokenWithoutPassword(){
        User user= new User();

        assertThat(user.getAccessToken(),is(""));
    }


}