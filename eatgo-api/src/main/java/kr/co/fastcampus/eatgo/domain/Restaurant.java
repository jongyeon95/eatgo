package kr.co.fastcampus.eatgo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
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

    private  String name;

    private  String address;

    @Transient
    private final List<MenuItem> menuItems=new ArrayList<>();

    public Restaurant(String name){
        this.name = name;
        this.address=null;
        this.id=null;
    }
    public Restaurant(String name,String address){
        this.name = name;
        this.address = address;
        this.id=null;
    }


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
