package com.potenday.anotherme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MBTIDetailsDto {
    public MBTIDetailsDto(String type, String name, String description, String imageName) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.imageName = imageName;
    }

    private String type;
    private String name;
    private String description;
    private String imageName;
}
