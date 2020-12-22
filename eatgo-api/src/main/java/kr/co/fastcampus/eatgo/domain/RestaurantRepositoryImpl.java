package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl(){
        restaurants.add(new Restaurant("Bob zip","Seoul",1004L));
        restaurants.add(new Restaurant("Cyber food", "Seoul",2020L));
    }

    @Override
    public List<Restaurant> findAll(){
        return restaurants;
    }

    @Override
    public  Restaurant findById(Long id){
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);//orElse 는 없는 경우
        return restaurant;
    }
}
