package com.potenday.anotherme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String personaName;
    private String mbtiType;

    public MemberDto(String email, String personaName, String mbtiType) {
        this.email = email;
        this.personaName = personaName;
        this.mbtiType = mbtiType;
    }
}
