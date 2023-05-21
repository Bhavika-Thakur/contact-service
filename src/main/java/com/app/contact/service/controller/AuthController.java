package com.app.contact.service.controller;

import com.app.contact.service.model.AuthUser;
import com.app.contact.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


        @Autowired
        private AuthService authService;

        @PostMapping("/authenticate")
        public String authenticateAndGetToken(@RequestBody AuthUser authUser){
            return authService.authenticateUser(authUser);
        }
}
