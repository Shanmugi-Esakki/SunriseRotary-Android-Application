package com.example.SunriseSignup.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SunriseSignup.Entity.Contact;
import com.example.SunriseSignup.Service.ContactService;

@RestController
@RequestMapping
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/api/co")
    public String saveContact(@RequestBody Contact contact) {
    	System.out.println("saving contact");
         contactService.saveContact(contact);
         return "Contact form submitted successfully";
    }
    
    @PostMapping("/members-only")
    public String membersOnly(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
        contacts.forEach(contact -> System.out.println(contact));
        }
        model.addAttribute("contacts", contacts);
        return "members-only";
    }
}

