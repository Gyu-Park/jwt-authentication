package com.jwt.jwtauthentication.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jwt.jwtauthentication.dto.SigninRequest;
import com.jwt.jwtauthentication.dto.SignupRequest;
import com.jwt.jwtauthentication.dto.TokenRequest;
import com.jwt.jwtauthentication.dto.AuthResponse;
import com.jwt.jwtauthentication.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/signin")
    public ResponseEntity<TokenRequest> signin(@RequestBody @Valid final SigninRequest signinRequest) {
        return ResponseEntity.ok(authService.signin(signinRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid final SignupRequest signupRequest) {
        authService.signup(signupRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> token() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenRequest> reissue(@RequestBody TokenRequest tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
