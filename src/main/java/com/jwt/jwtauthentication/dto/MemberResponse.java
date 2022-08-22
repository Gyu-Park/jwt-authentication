package com.jwt.jwtauthentication.dto;

import lombok.Getter;

import com.jwt.jwtauthentication.domain.Member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberResponse {

    private Long id;
    private String email;

    public static MemberResponse of(final Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }

}
