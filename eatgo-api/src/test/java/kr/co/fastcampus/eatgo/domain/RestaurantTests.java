package kr.co.fastcampus.eatgo.domain;


import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getName(), is("Bob zip"));
    }

    @Test
    public void information(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getInformation(),is("Bob zip in Seoul"));

    }
}