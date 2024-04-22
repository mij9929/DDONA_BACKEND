package com.potenday.anotherme.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CompletionRequest{

    private List<Map<String, String>> messages;

    public CompletionRequest() {
        this.messages = new ArrayList<>();
    }

    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }


    public void addMessage(String role, String content) {
        Map<String, String> messageMap = Map.of("role", role, "content", content);
        messages.add(messageMap);
    }
}
