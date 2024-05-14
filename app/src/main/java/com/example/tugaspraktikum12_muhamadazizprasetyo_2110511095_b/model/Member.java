package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.model;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String name, nim, email, groupTo, appName;

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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupTo() {
        return groupTo;
    }

    public void setGroupTo(String groupTo) {
        this.groupTo = groupTo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
