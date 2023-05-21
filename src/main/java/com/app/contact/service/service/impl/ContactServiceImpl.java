package com.app.contact.service.service.impl;

import com.app.contact.service.entity.Contact;
import com.app.contact.service.entity.UserInfo;
import com.app.contact.service.exception.CustomException;
import com.app.contact.service.model.ContactInfo;
import com.app.contact.service.model.SearchParameters;
import com.app.contact.service.repository.ContactRepository;
import com.app.contact.service.repository.UserInfoRepository;
import com.app.contact.service.service.ContactService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository contactRepository;

    @Override
        public String saveContact(ContactInfo contactInfo) {

            Contact contact = new Contact();
            contact.setUserCode(contactInfo.getUserCode());
            contact.setFirstName(contactInfo.getFirstName());
            contact.setLastName(contactInfo.getLastName());
            contact.setEmail(contactInfo.getEmail());
            contact.setPhoneNumber(contactInfo.getPhoneNumber());

            contactRepository.save(contact);

            return "Contact saved successfully";
        }

    @Override
    public String updateContact(ContactInfo contactInfo) {
            String code = contactInfo.getUserCode();
            Contact contact = contactRepository.findByUserCode(code)
                    .orElseThrow(()->new RuntimeException("Contact not found"));

            contact.setFirstName(contactInfo.getFirstName() != null ? contactInfo.getFirstName() : contact.getFirstName());
            contact.setLastName(contactInfo.getLastName() != null ? contactInfo.getLastName() : contact.getLastName());
            contact.setEmail(contactInfo.getEmail() != null ? contactInfo.getEmail() : contact.getEmail());
            contact.setPhoneNumber(contactInfo.getPhoneNumber() !=null ? contactInfo.getPhoneNumber() : contact.getPhoneNumber());

            contactRepository.save(contact);
            return "Contact updated successfully";
    }

    @Override
    public String deleteContact(String code) {
        Contact contact = contactRepository.findByUserCode(code)
                .orElseThrow(()->new CustomException("Contact not found"));

        contactRepository.delete(contact);
        return "Contact deleted successfully";
    }

    @Override
    public List<ContactInfo> getAllContacts() {

        List<Contact> contacts = contactRepository.findAll();
        List<ContactInfo> contactInfos = contacts.stream()
                .map(contact -> ContactInfo.builder()
                        .userCode(contact.getUserCode())
                        .firstName(contact.getFirstName())
                        .lastName(contact.getLastName())
                        .email(contact.getEmail())
                        .phoneNumber(contact.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
        return contactInfos;
    }

    @Override
    public List<ContactInfo> searchQueries(SearchParameters parameters) {
         String firstName = parameters.getFirstName();
         String lastName = parameters.getLastName();
         String email = parameters.getEmail();

         List<Contact>  list = contactRepository.findAll(byFirstName(firstName)
                 .or(byLastName(lastName))
                 .or(byEmail(email)));

         if(list == null){
             throw new CustomException("No Contact present for this search");
         }

         return list.stream().map(contact -> ContactInfo.builder()
                 .phoneNumber(contact.getPhoneNumber())
                 .email(contact.getEmail())
                 .firstName(contact.getFirstName())
                 .lastName(contact.getLastName())
                 .userCode(contact.getUserCode())
                 .build()).collect(Collectors.toList());
    }

    Specification<Contact> byFirstName(String firstName){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("firstName"),firstName);
    }

    Specification<Contact> byLastName(String lastName){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"),lastName);
    }

    Specification<Contact> byEmail(String email){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"),email);
    }


}
