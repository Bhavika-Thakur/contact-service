package com.app.contact.service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_contact")
public class Contact {

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            Long id;
            @Column(unique = true)
            String userCode;
            String firstName;
            String lastName;
            String email;
            String phoneNumber;
}
