package kr.co.fastcampus.eatgo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotNull
    private Long level;

    private String password;

    public boolean isAdmin(){

        return level>=3L;
    }
    public boolean isActive(){
        return level>0L;
    }

    public void deactivate() {
        level=0L;
    }

    @JsonIgnore
    public String getAccessToken() {
        if(password==null){
            return "";
        }
        return password.substring(0,10);
    }
}
