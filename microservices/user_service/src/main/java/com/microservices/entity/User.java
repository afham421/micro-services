package com.microservices.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;

    private String name;

    private String phone;

    @Transient  // for database ignore because we fecth data from other service
    List<Contact> contacts = new ArrayList<>();

    public User() {
    }

    public User(int id, String name, String phone, List<Contact> contacts) {
        this.user_id = id;
        this.name = name;
        this.phone = phone;
        this.contacts = contacts;
    }

    public User(int id, String name, String phone) {
        this.user_id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
