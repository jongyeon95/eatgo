package kr.co.fastcampus.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDto {
    private String accessToken;
}
