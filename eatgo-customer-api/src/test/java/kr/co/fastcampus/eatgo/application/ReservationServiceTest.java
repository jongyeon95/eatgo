package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Reservation;
import kr.co.fastcampus.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {


    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation(){
        Long userId=1004L;
        String name = "Tester";
        String date = "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;
        Long restaurantId = 1004L;
        Reservation mockReservation= Reservation.builder().name(name).build();

        given(reservationRepository.save(any())).will(invocation -> {
            Reservation reservation=invocation.getArgument(0);
            return reservation;
        });
        Reservation reservation=reservationService.addReservation(restaurantId,userId,name,date,time,partySize);
        assertThat(reservation.getName(),is(name));
        verify(reservationRepository).save(any(Reservation.class));
    }
}