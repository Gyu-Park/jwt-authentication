package com.jwt.jwtauthentication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;
    
}
