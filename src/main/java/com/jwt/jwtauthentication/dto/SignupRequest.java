package com.jwt.jwtauthentication.dto;

import javax.validation.constraints.Email;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwt.jwtauthentication.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class SignupRequest {
    
    @Email(message = "Not a valid email")
    private String email;

    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        Member member = new Member(email, passwordEncoder.encode(password));
        return member;
    }
    
}
