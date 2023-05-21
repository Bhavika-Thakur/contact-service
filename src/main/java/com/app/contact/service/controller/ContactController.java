package com.app.contact.service.controller;

import com.app.contact.service.entity.Contact;
import com.app.contact.service.model.ContactInfo;
import com.app.contact.service.model.SearchParameters;
import com.app.contact.service.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    ContactService contactService;
    //create a new contact
    @PostMapping("/saveContact")
    public ResponseEntity<String> saveContact(@RequestBody ContactInfo contactInfo){
        return new ResponseEntity<>(contactService.saveContact(contactInfo), HttpStatus.OK);

    }
    //update a contact
    @PatchMapping("/updateContact")
    public ResponseEntity<String> updateContact( @RequestBody ContactInfo contactInfo){
        return new ResponseEntity<>(contactService.updateContact(contactInfo),HttpStatus.OK);
    }
    //delete a contact

    @DeleteMapping("/deleteContact/{userCode}")
    public ResponseEntity<String> deleteContact(@PathVariable String userCode){
        return new ResponseEntity<>(contactService.deleteContact(userCode),HttpStatus.OK);
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<List<ContactInfo>> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(),HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ContactInfo>> searchQueries(@RequestBody SearchParameters parameters){
        return new ResponseEntity<>(contactService.searchQueries(parameters),HttpStatus.OK);
    }
}
