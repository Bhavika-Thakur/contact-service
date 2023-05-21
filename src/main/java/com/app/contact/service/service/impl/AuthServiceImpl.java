package com.app.contact.service.service.impl;

import com.app.contact.service.model.AuthUser;
import com.app.contact.service.security.JwtTokenProvider;
import com.app.contact.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;
    @Override
    public String authenticateUser(AuthUser authUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authUser.getUsername(),authUser.getPassword()));
            if(authentication.isAuthenticated()){
                return  tokenProvider.generateToken(authUser.getUsername());
            }else {
                throw new UsernameNotFoundException("Invalid User");
            }
    }
}
