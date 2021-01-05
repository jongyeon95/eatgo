package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(
                restaurantRepository,menuItemRepository,reviewRepository);

    }

    private void mockReviewRepository() {
        List<Review> reviews=new ArrayList<>();
        reviews.add(Review.builder()
        .name("BeRyong")
        .score(1)
        .description("Bad")
        .build());
        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants=new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address("Seoul")
                .build());
        restaurants.get(0).addMenuItem(new MenuItem("Kimchi"));
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Cyber food")
                .address("Seoul")
                .build());
        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul",1L)).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.ofNullable(restaurants.get(0)));

    }


    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        Review review=restaurant.getReviews().get(0);
        assertThat(review.getDescription(),is("Bad"));
        assertThat(menuItem.getName(), is("Kimchi"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
      restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants() {
        String region="Seoul";
        Long categoryId=1L;
        List<Restaurant> restaurants = restaurantService.getRestaurants(region,categoryId);
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void addRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        Restaurant saved=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.save(any())).willReturn(saved);
        Restaurant created =restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(),is(1004L));
    }

    @Test
    public void updateRestaurants(){
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        Restaurant updated=restaurantService.updateRestaurant(1004L,"Sool zip","Paju");
        assertThat(updated.getName(),is("Sool zip"));
        assertThat(updated.getAddress(),is("Paju"));

    }
}