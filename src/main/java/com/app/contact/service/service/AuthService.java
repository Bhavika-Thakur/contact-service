package com.app.contact.service.service;

import com.app.contact.service.model.AuthUser;

public interface AuthService {

    String authenticateUser(AuthUser authUser);
}
