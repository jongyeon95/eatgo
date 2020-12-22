package kr.co.fastcampus.eatgo.domain;

import lombok.Data;

@Data
public class MenuItem {
    private final String name;

    public MenuItem(String name){
        this.name=name;
    }
}
