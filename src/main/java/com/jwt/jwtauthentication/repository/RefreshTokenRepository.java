package com.jwt.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.jwtauthentication.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKey (String key);

}
