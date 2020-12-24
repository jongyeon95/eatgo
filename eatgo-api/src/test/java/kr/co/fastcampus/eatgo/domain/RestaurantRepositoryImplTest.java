package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class RestaurantRepositoryImplTest {
    @Test
    public void save(){
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        int oldCount =restaurantRepository.findAll().size();
        Restaurant restaurant=new Restaurant("Bob zip","Seoul");
        restaurantRepository.save(restaurant);
        assertThat(restaurant.getId(),is(1004L));
        int newCount=restaurantRepository.findAll().size();
        assertThat(newCount-oldCount,is(1));
    }

}