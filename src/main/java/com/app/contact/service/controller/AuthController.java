package com.app.contact.service.controller;

import com.app.contact.service.model.AuthResponse;
import com.app.contact.service.model.AuthUser;
import com.app.contact.service.model.User;
import com.app.contact.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


        @Autowired
        private AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@RequestBody User user){
                return new ResponseEntity<>(authService.registerUser(user),HttpStatus.OK);
        }

        @PostMapping("/authenticate")
        public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthUser authUser){
            return new ResponseEntity<>(authService.authenticateUser(authUser), HttpStatus.OK);
        }
}
