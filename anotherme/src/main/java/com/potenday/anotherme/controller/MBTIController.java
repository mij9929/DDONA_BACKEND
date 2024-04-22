package com.potenday.anotherme.controller;

import com.potenday.anotherme.dto.MBTIDetailsDto;
import com.potenday.anotherme.dto.MBTIDto;
import com.potenday.anotherme.model.Entity.MBTI;
import com.potenday.anotherme.model.QnA;
import com.potenday.anotherme.service.MBTIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/mbti")
@RestController
public class MBTIController {

    private final MBTIService mbtiService;


    @Autowired
    public MBTIController(MBTIService mbtiService) {
        this.mbtiService = mbtiService;
    }


    @GetMapping("/question/count")
    public ResponseEntity<Integer> getQuesionCount(){
        return ResponseEntity.ok(18);
    }

    @PostMapping("/calculate")
    public ResponseEntity<MBTIDetailsDto> getCharcterInfo(@RequestBody MBTIDto mbtiDto){
        MBTIDetailsDto mbtiDetailsDto = mbtiService.calculateMBTI(mbtiDto);
        return ResponseEntity.ok().body(mbtiDetailsDto);
    }

    @GetMapping("/question/{no}")
    public QnA getQunestionAndAnswers(@PathVariable int no){
        String question = mbtiService.getQuestion(no);
        String[] answer = mbtiService.getAnswer(no);
        return new QnA(question, answer);
    }



}
