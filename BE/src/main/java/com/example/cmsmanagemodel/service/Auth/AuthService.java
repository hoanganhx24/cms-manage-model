package com.example.cmsmanagemodel.service.Auth;

import com.nimbusds.jwt.SignedJWT;

import javax.naming.AuthenticationException;

public interface AuthService {
    SignedJWT verifyToken(String token, boolean isRefresh) throws AuthenticationException;
}
