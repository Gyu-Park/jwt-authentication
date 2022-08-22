package com.jwt.jwtauthentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.jwtauthentication.repository.MemberRepository;
import com.jwt.jwtauthentication.dto.MemberResponse;
import com.jwt.jwtauthentication.domain.Member;
import com.jwt.jwtauthentication.config.SecurityUtil;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    
    public List<Member> findByRole(String role) {

        // code needed here

        return memberRepository.findAll();
    }

    public MemberResponse getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponse::of)
                .orElseThrow(() -> new RuntimeException("There's no that member info."));
    }

    public MemberResponse getCurrMemberInfo(String email) {
        return memberRepository.findByEmail(SecurityUtil.getCurrentMemberId())
                .map(MemberResponse::of)
                .orElseThrow(() -> new RuntimeException("There's no login member."));
    }
}
