package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();

        restaurantService = new RestaurantService(
                restaurantRepository,menuItemRepository);

    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants=new ArrayList<>();
        restaurants.add(new Restaurant("Bob zip","Seoul",1004L));
        restaurants.get(0).addMenuItem(new MenuItem("Kimchi"));
        restaurants.add(new Restaurant("Cyber food","Seoul",2020L));
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.ofNullable(restaurants.get(0)));

    }


    @Test
    public void getRestaurant() {
        Optional<Restaurant> restaurant = restaurantService.getRestaurant(1004L);
        assertThat(restaurant.get().getId(), is(1004L));
        MenuItem menuItem = restaurant.get().getMenuItems().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void addRestaurant(){
        Restaurant restaurant=new Restaurant("Bob zip","Seoul");
        Restaurant saved=new Restaurant("Bob zip","Seoul",1004L);

        given(restaurantRepository.save(any())).willReturn(saved);
        Restaurant created =restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(),is(1004L));
    }
}