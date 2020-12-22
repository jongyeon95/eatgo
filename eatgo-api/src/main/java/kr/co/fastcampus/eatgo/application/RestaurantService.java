package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Restaurant restaurant=restaurantRepository.findById(id);
        List<MenuItem> menuItems= menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        return  restaurant;
    };
}
