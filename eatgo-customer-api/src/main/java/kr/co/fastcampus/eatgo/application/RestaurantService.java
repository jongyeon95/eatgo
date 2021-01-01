package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository,ReviewRepository reviewRepository){
        this.restaurantRepository=restaurantRepository;
        this.menuItemRepository=menuItemRepository;
        this.reviewRepository=reviewRepository;
    }

    public List<Restaurant> getRestaurants(){
        return  restaurantRepository.findAll();
    };


    public Restaurant getRestaurant(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        List<MenuItem> menuItems= menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        List<Review> review=reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(review);

        return  restaurant;
    };

    public Restaurant addRestaurant(Restaurant restaurant) {
       return restaurantRepository.save(restaurant);

    }

    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateRestaurant(name,address);
        return restaurant;
    }
}
