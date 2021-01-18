package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReservationService;
import kr.co.fastcampus.eatgo.domain.Reservation;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReservationService reservationService;


    @Test
    public void create() throws Exception {
        Long userId=1004L;
        String name = "Tester";
        String date = "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;
        Reservation mockReservation=Reservation.builder().id(12L).build();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJUZXN0ZXIifQ.I4DNdunio2m54tfUEaXHC_E-gvCQo6ZhHO15Ewkat6U";
        given(reservationService.addReservation(any(),any(),any(),any(),any(),any())).willReturn(mockReservation);
        mvc.perform(post("/restaurants/1004/reservations")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2019-12-24\",\"time\":\"20:00\",\"partySize\":20}"))
                .andExpect(status().isCreated());


        verify(reservationService).addReservation(eq(1004L), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }




}