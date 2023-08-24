package com.demo.jwt.backend.model.entity;

import jakarta.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Token() {
    }

    public Token( String token, TokenType tokenType, boolean expired, boolean revoked, UserEntity user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Token setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public Token setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public boolean isExpired() {
        return expired;
    }

    public Token setExpired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public Token setRevoked(boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public Token setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
