package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReviewService;
import kr.co.fastcampus.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValid() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJUZXN0ZXIifQ.I4DNdunio2m54tfUEaXHC_E-gvCQo6ZhHO15Ewkat6U";
        given(reviewService.addReview(1L,"Tester",3,"Mat-it-da")).willReturn(
                Review.builder().id(1234L)
                        .name("Tester")
                        .score(3)
                        .description("mat-it-da").build()
        );
        mvc.perform(post("/restaurants/1/reviews")
                .header("Authorization","Bearer "+token)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"score\":3,\"description\":\"Mat-it-da\"}"))
                .andExpect(status().isCreated())
        .andExpect(header().string("location","/restaurants/1/reviews/1234"));

        verify(reviewService).addReview(eq(1L),eq("Tester"),eq(3),eq("Mat-it-da"));
    }

    @Test
    public void createWithInvalid() throws Exception {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService,never()).addReview(any(),any(),any(),any());
    }

}