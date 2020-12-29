package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private  RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository){
        this.restaurantRepository=restaurantRepository;
        this.menuItemRepository=menuItemRepository;
    }

    public List<Restaurant> getRestaurants(){
        return  restaurantRepository.findAll();
    };


    public Restaurant getRestaurant(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        List<MenuItem> menuItems= menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
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
