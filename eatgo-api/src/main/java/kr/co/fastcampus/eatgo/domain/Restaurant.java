package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class Restaurant {
    private final String name;
    private final String address;
    private final Long id;

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
    public String getName() {
        return name;
    }
    public String getInformation(){
        return name+" in "+address;
    }
}
