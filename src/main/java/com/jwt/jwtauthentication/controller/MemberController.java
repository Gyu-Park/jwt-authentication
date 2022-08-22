package com.jwt.jwtauthentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jwt.jwtauthentication.dto.MemberResponse;
import com.jwt.jwtauthentication.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    @GetMapping("/{email}")
    public ResponseEntity<MemberResponse> getMemberInfo (@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }

    @GetMapping("/curr-member")
    public ResponseEntity<MemberResponse> getCurrMemberInfo (@PathVariable String email) {
        return ResponseEntity.ok(memberService.getCurrMemberInfo(email));
    }

}
