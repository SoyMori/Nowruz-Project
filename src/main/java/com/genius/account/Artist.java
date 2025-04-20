package com.genius.account;

import com.genius.model.Song;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account {
    private List<Song> songs;
    private List<User> followers;

    public Artist(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
        this.songs = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addFollower(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
        }
    }

    public List<User> getFollowers() {
        return followers;
    }

    @Override
    public void showProfile() {
        System.out.println("🎤 Artist Profile");
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Songs Released: " + songs.size());
    }
}
