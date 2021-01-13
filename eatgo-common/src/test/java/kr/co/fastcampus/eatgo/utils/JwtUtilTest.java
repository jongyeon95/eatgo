package kr.co.fastcampus.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JwtUtilTest {

    private static final String SECRET="12345678901234567890123456789012";
    private JwtUtil jwtUtil;
    @Before
    public void setUp(){
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken(){
        String token = jwtUtil.createToken(1004L,"Tester");

        assertThat(token,containsString("."));
    }

    @Test
    public void getClaims(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJUZXN0ZXIifQ.I4DNdunio2m54tfUEaXHC_E-gvCQo6ZhHO15Ewkat6U";
        Claims claims = jwtUtil.getClaims(token);
        assertThat(claims.get("userId",Long.class),is(1004L));
        assertThat(claims.get("name"),is("Tester"));
    }

}