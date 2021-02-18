package com.example.hitproject.objects;

import java.io.Serializable;

public class Person implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String address;
    private String birthDate;

    public Person () {
        email = "";
        name = "";
        phone = "";
        address = "";
        birthDate = "";

    }

    public Person(String email, String name, String phone, String address, String birthDate) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}