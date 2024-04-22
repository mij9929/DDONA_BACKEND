package com.potenday.anotherme.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@Schema(description = "mbti 점수")
public class MBTIDto {
    @Schema(example = "{\"E\":1, \"I\":5, \"S\":4, \"N\":2, \"T\":3, \"F\":4, \"P\": 2, \"J\":4}")
    Map<String, Integer> mbtiScores;
}
