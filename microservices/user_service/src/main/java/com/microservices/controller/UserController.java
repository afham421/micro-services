package com.microservices.controller;

import com.microservices.dao.UserDao;
import com.microservices.entity.User;
import com.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserDao userDao;

    @GetMapping("/get")
//    @ResponseBody
    public User getUser() {
//        System.out.println("if you wants to print all as it is then you need to put @ResponseBody annotation but if you use @RestController then you dont need @ResponseBody");

        User allUser = (User) userService.getAllUser();
        List contacts = restTemplate.getForObject("http://localhost:9999/contact/get/userid", List.class); //list.class list main data get kerny k liye
        allUser.setContacts(contacts);


        return allUser;
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") int id){

       User user =this.userService.getUser(id);

        List contact = restTemplate.getForObject("http://localhost:9999/contact/get/" + user.getUser_id(), List.class);

user.setContacts(contact);
return user;

    }

    @PostMapping("/post")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("user successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        try {
            userService.update(user, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
