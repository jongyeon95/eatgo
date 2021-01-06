package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        userService.addUser(resource.getEmail(),resource.getName());
        String url = "/users/"+resource.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/users/{userId}")
    public String update(@PathVariable("userId") Long id,@RequestBody User resource) throws URISyntaxException {
        userService.updateUser(id,resource.getEmail(),resource.getName(),resource.getLevel());

        return "{}";
    }

    @DeleteMapping("/users/{userId}")
    public String delete(@PathVariable("userId") Long id){
        userService.deactiveUser(id);
        return "{}";
    }


}
