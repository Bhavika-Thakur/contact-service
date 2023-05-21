package com.app.contact.service.service.impl;

import com.app.contact.service.entity.UserInfo;
import com.app.contact.service.exception.CustomException;
import com.app.contact.service.model.AuthResponse;
import com.app.contact.service.model.AuthUser;
import com.app.contact.service.model.User;
import com.app.contact.service.repository.UserInfoRepository;
import com.app.contact.service.security.JwtTokenProvider;
import com.app.contact.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserInfoRepository userInfoRepository;
    @Override
    public AuthResponse authenticateUser(AuthUser authUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authUser.getUsername(),authUser.getPassword()));
            if(authentication.isAuthenticated()){
                String authToken = tokenProvider.generateToken(authUser.getUsername());
                return AuthResponse.builder()
                        .message("Valid User")
                        .authToken(authToken)
                        .build();
            }else {
                throw new CustomException("Invalid User");
            }
    }

    @Override
    public String registerUser(User user) {
            UserInfo userInfo = UserInfo.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles("")
                    .build();
            userInfoRepository.save(userInfo);
            return "User Information saved successfully";
    }

}
