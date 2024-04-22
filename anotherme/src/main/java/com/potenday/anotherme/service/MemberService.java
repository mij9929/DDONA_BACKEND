package com.potenday.anotherme.service;

import com.potenday.anotherme.dto.MBTIDto;
import com.potenday.anotherme.dto.MemberDto;
import com.potenday.anotherme.dto.SignUpDto;
import com.potenday.anotherme.jwt.JwtToken;
import com.potenday.anotherme.jwt.JwtTokenProvider;
import com.potenday.anotherme.model.Entity.Member;
import com.potenday.anotherme.repository.MBTIRepository;
import com.potenday.anotherme.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
@Transactional
public class MemberService {
    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public MemberService(BCryptPasswordEncoder encoder, MemberRepository memberRepository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.encoder = encoder;
        this.memberRepository = memberRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Member signUp(SignUpDto signUpDto){

        boolean isUserExist = memberRepository.existsByEmail(signUpDto.getEmail());
        if(isUserExist) throw new RuntimeException("이미 존재하는 이메일");
        Member member = new Member();
        member.setEmail(signUpDto.getEmail());
        member.setPassword(encoder.encode(signUpDto.getPassword()));
        member.setMbtiType(signUpDto.getMbtiType());
        member.setPersonaName(signUpDto.getPersonaName());
        member.setRoles(Collections.singletonList("USER"));
        return memberRepository.save(member);

    }

    public JwtToken login(@RequestBody String email, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    public String getMemberMBTIType(String userEmail){
        return memberRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")).getMbtiType();
    }

    public MemberDto getMember(String userEmail){
        Member member =  memberRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return MemberDto.builder().email(userEmail).personaName(member.getPersonaName()).mbtiType(member.getMbtiType()).build();
    }
}



