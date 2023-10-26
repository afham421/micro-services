package com.microservices.controller;

import com.microservices.entity.Contact;
import com.microservices.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping("/get")
//    @ResponseBody
    public ResponseEntity<List<Contact>> getContact() {
//        System.out.println("if you want to print all as it is then you need to put @ResponseBody annotation but if you use @RestController then you dont need @ResponseBody");

        List<Contact> users = contactService.getAllContact();


        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @RequestMapping("/get/userid/{userId}")
    public ResponseEntity<List<Contact>> getContact(@PathVariable("userId") int userId){

        List<Contact> contact = contactService.getContact(userId);
        if (contact.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Contact>> getContacts(@PathVariable("id") int id){

        Optional<Contact> contact = contactService.getContacts(id);
        if (contact==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    @PostMapping("/post")
    public ResponseEntity<String> addContact(@RequestBody Contact contact) {
        try {
            contactService.addContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body("user successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable("id") int id) {
        try {
            contactService.update(contact, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") int id) {
        try {
            contactService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
