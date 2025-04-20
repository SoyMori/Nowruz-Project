package com.genius.account;

import com.genius.model.Song;

import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private List<Artist> followedArtists;
    private List<String> notifications;
    private List<Song> recentlyViewedSongs;

    public User(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
        this.followedArtists = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.recentlyViewedSongs = new ArrayList<>();
    }

    public void followArtist(Artist artist) {
        if (!followedArtists.contains(artist)) {
            followedArtists.add(artist);
            artist.addFollower(this);
            System.out.println("✅ You followed " + artist.getUsername());
        } else {
            System.out.println("⚠️ You already follow " + artist.getUsername());
        }
    }

    public void listFollowedArtists() {
        System.out.println("👥 Followed Artists:");
        if (followedArtists.isEmpty()) {
            System.out.println("⚠️ You are not following any artists yet.");
        }
        for (Artist artist : followedArtists) {
            System.out.println("- " + artist.getUsername());
        }
    }

    public void addNotification(String message) {
        notifications.add(message);
    }

    public void showNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("📭 No new notifications.");
            return;
        }

        System.out.println("\n📩 You have " + notifications.size() + " new notifications:");
        for (String msg : notifications) {
            System.out.println("- " + msg);
        }

        notifications.clear();
    }

    public void addToHistory(Song song) {
        recentlyViewedSongs.remove(song);
        recentlyViewedSongs.add(0, song);
        if (recentlyViewedSongs.size() > 10) {
            recentlyViewedSongs.remove(recentlyViewedSongs.size() - 1);
        }
    }

    public void showHistory() {
        if (recentlyViewedSongs.isEmpty()) {
            System.out.println("📭 No recently viewed songs.");
            return;
        }

        System.out.println("\n📜 Recently Viewed Songs:");
        for (int i = 0; i < recentlyViewedSongs.size(); i++) {
            Song song = recentlyViewedSongs.get(i);
            System.out.println((i + 1) + ". " + song.getTitle()
                    + " by " + song.getMainArtist().getUsername()
                    + " | Views: " + song.getViews());
        }
    }

    @Override
    public void showProfile() {
        System.out.println("👤 User Profile");
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
    }
}
