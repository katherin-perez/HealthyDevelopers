package com.example.healthydevelopers.model;

public class User {
    private int id;
    private String name;
    private String last_name;
    private char sex;
    private String mail;
    private String password;
    private String phone;
    private String direction;

    public User(int id, String name, String last_name, char sex, String mail, String password, String phone, String direction) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.sex = sex;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
