package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.EmailNotExistedException;
import kr.co.fastcampus.eatgo.application.PasswordWrongException;
import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void createWithValidAttribute() throws Exception {
        String email="tester@example.com";
        String password="test";
        User mockUser=User.builder().password("ACCESSTOKEN").build();
        given(userService.authenticate(email,password)).willReturn(mockUser);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\"," +
                        "\"name\":\"Tester\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(("{\"accessToken\":\"ACCESSTOKE\"}")));

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        given(userService.authenticate("tester@example.com","x")).willThrow(PasswordWrongException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\"," +
                        "\"name\":\"Tester\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("tester@example.com"), eq("x"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("tester@example.com","x")).willThrow(PasswordWrongException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\"," +
                        "\"name\":\"Tester\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("tester@example.com"), eq("x"));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("x@example.com","test")).willThrow(EmailNotExistedException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@example.com\"," +
                        "\"name\":\"Tester\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("x@example.com"), eq("test"));
    }

}