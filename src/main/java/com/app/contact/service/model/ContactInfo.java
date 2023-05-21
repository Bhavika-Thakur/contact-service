package com.app.contact.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfo {

    String userCode;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
