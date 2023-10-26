package com.microservices.service;

import com.microservices.dao.ContactDao;
import com.microservices.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    public List<Contact> getAllContact(){
        List<Contact> all = contactDao.findAll();
        return all;
    }
    @Override
    public List<Contact> getContact(Integer userId) {
        return contactDao.findById(userId).stream().filter(contact -> contact.getUserId()==userId).collect(Collectors.toList());
    }

    public Optional<Contact> getContacts(int id){
        Optional<Contact> contact= null;
        try {
             contact = contactDao.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return contact;
    }


    public void addContact(Contact contact){
        try {
            contactDao.save(contact);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Contact update(Contact contact , int id){
        contact.setId(id); // ab agr user jo id dy ga woh is address ko assign ho jay gi // or wohi id wala address update ho ga
        Contact update = contactDao.save(contact);
        return update;
    }

    public void delete(int id){
        contactDao.deleteById(id);
    }



}
