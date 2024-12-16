package com.example.SunriseSignup.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SunriseSignup.Entity.Contact;
import com.example.SunriseSignup.Repository.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
