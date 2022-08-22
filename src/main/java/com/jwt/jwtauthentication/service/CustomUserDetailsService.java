package com.jwt.jwtauthentication.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.jwtauthentication.domain.Member;
import com.jwt.jwtauthentication.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> There's no such User."));
    }

    // return UserDetails object if the member exists
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        return new User(
                member.getEmail(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
    
}
