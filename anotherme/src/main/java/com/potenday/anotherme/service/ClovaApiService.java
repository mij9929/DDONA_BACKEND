package com.potenday.anotherme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potenday.anotherme.model.SlidingWindow;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ClovaApiService {

    private final String slidingWindowHost = "";
    private final String slidingWindowApiKey = "";
    private final String slidingWindowApiKeyPrimaryVal = "";
    private final String slidingWindowRequestId = "";

    private final ObjectMapper objectMapper;

    public ClovaApiService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Map<String, String>> getSlidingWindowMessage(List<Map<String, String>> message) throws JsonProcessingException {
        SlidingWindow slidingWindow = new SlidingWindow(message, 2000);
        HttpHeaders SlidingWindowheaders = new HttpHeaders();
        SlidingWindowheaders.add("X-NCP-CLOVASTUDIO-API-KEY", slidingWindowApiKey);
        SlidingWindowheaders.add("X-NCP-APIGW-API-KEY", slidingWindowApiKeyPrimaryVal);
        SlidingWindowheaders.add("X-NCP-CLOVASTUDIO-REQUEST-ID", slidingWindowRequestId);
        SlidingWindowheaders.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<SlidingWindow> SlidingWindowRequestEntity = RequestEntity
                .post(slidingWindowHost + "/testapp/v1/api-tools/sliding/chat-messages/HCX-002/9e61caa69e4943508be784a334141090")
                .headers(SlidingWindowheaders)
                .body(slidingWindow);

        ResponseEntity<String> slidingWindowResponseEntity = new RestTemplate().exchange(SlidingWindowRequestEntity, String.class);
        String swResponseBody = slidingWindowResponseEntity.getBody();

        Map<String, Object> data = objectMapper.readValue(swResponseBody, new TypeReference<Map<String, Object>>() {});
        List<Map<String, String>> messages = (List<Map<String, String>>) ((Map<String, Object>) data.get("result")).get("messages");

        return messages;
    }



}
