package com.genius.account;

public abstract class Account {
    protected String name;
    protected int age;
    protected String email;
    protected String username;
    protected String password;

    public Account(String name, int age, String email, String username, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public abstract void showProfile();

    @Override
    public String toString() {
        return username;
    }
}
