package com.microservices.service;

import com.microservices.entity.Contact;

import java.util.List;

public interface ContactService
{

    public List<Contact> getContact(Integer userId);
}
