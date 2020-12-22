package kr.co.fastcampus.eatgo.domain;


import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Data
public class Restaurant {
    private final String name;
    private final String address;
    private final Long id;
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
    public Restaurant(String name,String address,Long id){
        this.name = name;
        this.address = address;
        this.id=id;
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
}
