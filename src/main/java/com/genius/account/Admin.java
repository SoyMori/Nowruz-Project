package com.genius.account;

public class Admin extends Account {
    public Admin(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
    }

    @Override
    public void showProfile() {
        System.out.println("🔧 Admin Profile");
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
    }
}
