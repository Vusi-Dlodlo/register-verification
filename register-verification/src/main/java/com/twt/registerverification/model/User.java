package com.twt.registerverification.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private String email;
    private String password;

    private boolean isEnabled;

    public User() {
    }

    public User(Long id, String name, String email, String password, boolean isEnabled) {
        Id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public User(String name, String email, String password, boolean isEnabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
    }


}
