package com.potenday.anotherme.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CacheCompletionMessages {
    private List<Map<String, String>> messages;

}
