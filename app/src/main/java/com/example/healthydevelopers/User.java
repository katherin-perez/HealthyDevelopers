package com.example.healthydevelopers;

public class User {
    private String name;
    private String lastName;
    private String sex;
    private String mail;
    private String phone;
    private String direction;

    public User(String name, String lastName, String sex, String mail, String phone, String direction) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.mail = mail;
        this.phone = phone;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
