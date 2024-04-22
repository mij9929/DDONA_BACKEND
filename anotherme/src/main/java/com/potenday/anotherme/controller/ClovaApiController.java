package com.potenday.anotherme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potenday.anotherme.dto.AssistantAnswerDto;
import com.potenday.anotherme.dto.ChatRecommendDto;
import com.potenday.anotherme.model.CompletionRequest;
import com.potenday.anotherme.dto.UserRequestDto;
import com.potenday.anotherme.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clova")
public class ClovaApiController {

    private final String host = "";
    private final String apiKey = "";
    private final String apiKeyPrimaryVal = "";
    private final String requestId = "";

    private final ObjectMapper objectMapper;
    private final CacheService cacheService;
    private final ClovaApiService clovaApiService;
    private final MemberService memberService;
    private final MBTIService mbtiService;
    private final ChatService chatService;

    @Autowired
    public ClovaApiController(ObjectMapper objectMapper, CacheService cacheService, ClovaApiService clovaApiService, MemberService memberService, MBTIService mbtiService, ChatService chatService){
        this.objectMapper = objectMapper;
        this.cacheService = cacheService;
        this.clovaApiService = clovaApiService;
        this.memberService = memberService;
        this.mbtiService = mbtiService;
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    public ResponseEntity<AssistantAnswerDto> execute(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetails member) throws JsonProcessingException {
        String userAnswer = userRequestDto.getMessage();
        String userEmail = member.getUsername();
        String personaName = memberService.getMember(userEmail).getPersonaName();
        String mbti = memberService.getMemberMBTIType(userEmail);
        String systemMessage = mbtiService.getSystemMessageByMBTI(mbti);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-NCP-CLOVASTUDIO-API-KEY", apiKey);
        headers.add("X-NCP-APIGW-API-KEY", apiKeyPrimaryVal);
        headers.add("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        CompletionRequest completionRequest = new CompletionRequest();

        // 캐시 밸류 로드 (해당 키가 존재한다면)
        if(ObjectUtils.isEmpty(cacheService.getPrevMessages(userEmail))){
            completionRequest.addMessage("system", "이름은 " + personaName + "\n" + systemMessage);
        } else{
            completionRequest.setMessages(cacheService.getPrevMessages(userEmail).getMessages());
        }

        completionRequest.addMessage("user", userAnswer);

        List<Map<String, String>> messages = clovaApiService.getSlidingWindowMessage(completionRequest.getMessages());
        completionRequest.setMessages(messages);

        RequestEntity<CompletionRequest> requestEntity = RequestEntity
                .post(host + "/testapp/v1/chat-completions/HCX-002")
                .headers(headers)
                .body(completionRequest);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        String assistantContent = objectMapper.readTree(responseBody).get("result").get("message").get("content").asText();

        // 캐시 저장(수정)
        completionRequest.addMessage("assistant", assistantContent);
        cacheService.saveMessages(userEmail, completionRequest.getMessages());

        AssistantAnswerDto assistantAnswerDto = new AssistantAnswerDto(assistantContent);

        return ResponseEntity.ok().body(assistantAnswerDto);
    }

    @GetMapping("/messages/")
    public ResponseEntity<ChatRecommendDto> getRecommendMessages(){
        return ResponseEntity.ok(chatService.generateMessages());
    }



}
