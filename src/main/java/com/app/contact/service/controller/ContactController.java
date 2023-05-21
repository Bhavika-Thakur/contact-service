package com.app.contact.service.controller;

import com.app.contact.service.entity.Contact;
import com.app.contact.service.model.ContactInfo;
import com.app.contact.service.model.SearchParameters;
import com.app.contact.service.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {


    @Autowired
    ContactService contactService;
    //create a new contact
    @PostMapping("/saveContact")
    public String saveContact(@RequestBody ContactInfo contactInfo){
        return contactService.saveContact(contactInfo);

    }
    //update a contact
    @PatchMapping("/updateContact")
    public String updateContact( @RequestBody ContactInfo contactInfo){
        return contactService.updateContact(contactInfo);

    }
    //delete a contact

    @DeleteMapping("/deleteContact/{userCode}")
    public String deleteContact(@PathVariable String userCode){
        return contactService.deleteContact(userCode);
    }

    @GetMapping("/getAllContacts")
    public List<ContactInfo> getAllContacts(){
        return contactService.getAllContacts();
    }

    //get all contacts
    //get a contact by id

    @PostMapping("/search")
    public List<ContactInfo> searchQueries(@RequestBody SearchParameters parameters){
        return contactService.searchQueries(parameters);
    }
}
