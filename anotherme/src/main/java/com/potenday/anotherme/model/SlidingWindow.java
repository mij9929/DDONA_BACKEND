package com.potenday.anotherme.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SlidingWindow {

    private List<Map<String, String>> messages;
    private int maxTokens;

    public SlidingWindow(List<Map<String, String>> messages, int maxTokens) {
        this.messages = messages;
        this.maxTokens = maxTokens;
    }
}
