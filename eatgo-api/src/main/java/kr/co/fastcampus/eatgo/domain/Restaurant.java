package kr.co.fastcampus.eatgo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private  String name;

    private  String address;

    @Transient
    private final List<MenuItem> menuItems=new ArrayList<>();


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
}
