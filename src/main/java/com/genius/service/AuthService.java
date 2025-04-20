package com.genius.service;

import com.genius.account.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthService {
    private List<Account> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private List<Artist> pendingArtists = new ArrayList<>(); // Unverified artists

    public Account login() {
        System.out.println("\n🔐 Login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                System.out.println("✅ Login successful. Welcome " + acc.getUsername() + "!");
                return acc;
            }
        }

        System.out.println("❌ Invalid credentials.");
        return null;
    }

    public Account register() {
        System.out.println("\n📝 Register");
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println("Choose role:\n1. User\n2. Artist\n3. Admin ");
        String choice = scanner.nextLine();

        Account newAccount = null;
        switch (choice) {
            case "1":
                newAccount = new User(name, age, email, username, password);
                break;
            case "2":
                Artist artist = new Artist(name, age, email, username, password);
                pendingArtists.add(artist);
                System.out.println("⏳ Artist registration sent for admin approval. Wait for verification.");
                return null;

            case "3":
                newAccount = new Admin(name, age, email, username, password);
                break;
            default:
                System.out.println("❌ Invalid role choice.");
                return null;
        }

        accounts.add(newAccount);
        System.out.println("✅ Registration successful for " + newAccount.getUsername());
        return newAccount;
    }

    public void seedTestData() {
        accounts.add(new User("Ali", 20, "ali@gmail.com", "ali20", "1234"));
        accounts.add(new Artist("Shahin", 30, "shahin@music.com", "shahinmusic", "abcd"));
        accounts.add(new Admin("Admin", 35, "admin@admin.com", "admin", "admin"));
    }

    public void approveArtists() {
        Scanner scanner = new Scanner(System.in);

        if (pendingArtists.isEmpty()) {
            System.out.println("✅ No artists pending approval.");
            return;
        }

        System.out.println("\n📝 Pending Artist Accounts:");
        for (int i = 0; i < pendingArtists.size(); i++) {
            Artist artist = pendingArtists.get(i);
            System.out.println((i + 1) + ". " + artist.getUsername() + " - " + artist.getEmail());
        }

        System.out.print("Enter number of artist to approve (0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0) {
            return;
        }

        if (choice < 1 || choice > pendingArtists.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Artist approved = pendingArtists.remove(choice - 1);
        accounts.add(approved);
        System.out.println("✅ Artist " + approved.getUsername() + " approved and added to system.");
    }


    public List<Account> getAllAccounts() {

        return accounts;
    }

    public AuthService(List<Account> accounts) {
        this.accounts = accounts;
    }

}
