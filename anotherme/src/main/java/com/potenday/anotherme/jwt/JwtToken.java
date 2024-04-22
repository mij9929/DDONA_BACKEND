package com.potenday.anotherme.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class JwtToken {
    private String grantType;
    private String accessToken;
}
