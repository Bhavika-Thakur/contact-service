package com.app.contact.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    String message;
    String authToken;
}
