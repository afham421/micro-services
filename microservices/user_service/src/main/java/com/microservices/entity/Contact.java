package com.microservices.entity;

public class Contact {

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "contact_id", nullable = false)
    private int id;

    public String contactName;
    public String email;
    public int userId;

    public Contact() {
    }

    public Contact(int id, String contactName, String email, int userId) {
        this.id = id;
        this.contactName = contactName;
        this.email = email;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
