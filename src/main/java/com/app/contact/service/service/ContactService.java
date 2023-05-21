package com.app.contact.service.service;

import com.app.contact.service.model.ContactInfo;
import com.app.contact.service.model.SearchParameters;
import org.springframework.core.annotation.MergedAnnotations;

import java.util.List;

public interface ContactService {

    String saveContact(ContactInfo contactInfo);

    String updateContact(ContactInfo contactInfo);

    String deleteContact(String code);

    List<ContactInfo> getAllContacts();

    List<ContactInfo> searchQueries(SearchParameters parameters);
}
