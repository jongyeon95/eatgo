package kr.co.fastcampus.eatgo.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.eatgo.application.ReviewService;
import kr.co.fastcampus.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantsId}/reviews")
    public ResponseEntity<?> create(Authentication authentication,
                                    @Valid @RequestBody Review resource,
                                    @PathVariable("restaurantsId") Long id ) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        String name= claims.get("name",String.class);
        Integer score= resource.getScore();
        String description=resource.getDescription();
        Review review=reviewService.addReview(id,name,score,description);
        String url="/restaurants/"+id+"/reviews/"+review.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
