package com.potenday.anotherme.controller;

import com.potenday.anotherme.dto.LoginDto;
import com.potenday.anotherme.dto.MemberDto;
import com.potenday.anotherme.dto.SignUpDto;
import com.potenday.anotherme.jwt.JwtToken;
import com.potenday.anotherme.model.Entity.Member;
import com.potenday.anotherme.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDto> register(@RequestBody SignUpDto signUpDto){
        Member member = memberService.signUp(signUpDto);
        return ResponseEntity.ok(new MemberDto(member.getEmail(), member.getPersonaName(), member.getMbtiType()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginDto loginDto){
        JwtToken token = memberService.login(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberDto> memberInfo(@AuthenticationPrincipal UserDetails member){
        MemberDto memberDto = memberService.getMember(member.getUsername());
        return ResponseEntity.ok(memberDto);
    }
}
