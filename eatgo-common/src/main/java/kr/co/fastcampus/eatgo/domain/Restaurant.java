package kr.co.fastcampus.eatgo.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    private  Long id;

    @NotNull
    private Long categoryId;

    @NotEmpty
    private  String name;

    private  String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<MenuItem> menuItems=new ArrayList<>();

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getInformation(){
        return name+" in "+address;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem m : menuItems){
            addMenuItem(m);
        }
    }

    public void updateRestaurant(String name,String address){
        this.name=name;
        this.address=address;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews=new ArrayList<>(reviews);
    }
}
