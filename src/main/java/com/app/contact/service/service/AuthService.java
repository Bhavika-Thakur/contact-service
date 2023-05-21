package com.app.contact.service.service;

import com.app.contact.service.model.AuthResponse;
import com.app.contact.service.model.AuthUser;
import com.app.contact.service.model.User;

public interface AuthService {

    AuthResponse authenticateUser(AuthUser authUser);

    String registerUser(User user);
}
