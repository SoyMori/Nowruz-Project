package com.genius.data;

import com.genius.account.Account;
import com.genius.model.Album;
import com.genius.model.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String ACCOUNTS_FILE = "accounts.json";
    private static final String SONGS_FILE = "songs.json";
    private static final String ALBUMS_FILE = "albums.json";

    // ----------------- ACCOUNTS -----------------
    public static void saveAccounts(List<Account> accounts) {
        try (Writer writer = new FileWriter(ACCOUNTS_FILE)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            System.out.println("❌ Failed to save accounts: " + e.getMessage());
        }
    }

    public static List<Account> loadAccounts() {
        try (Reader reader = new FileReader(ACCOUNTS_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Account>>(){}.getType());
        } catch (IOException e) {
            System.out.println("📂 No saved accounts found.");
            return new ArrayList<>();
        }
    }

    // ----------------- SONGS -----------------
    public static void saveSongs(List<Song> songs) {
        try (Writer writer = new FileWriter(SONGS_FILE)) {
            gson.toJson(songs, writer);
        } catch (IOException e) {
            System.out.println("❌ Failed to save songs: " + e.getMessage());
        }
    }

    public static List<Song> loadSongs() {
        try (Reader reader = new FileReader(SONGS_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Song>>(){}.getType());
        } catch (IOException e) {
            System.out.println("📂 No saved songs found.");
            return new ArrayList<>();
        }
    }

    // ----------------- ALBUMS -----------------
    public static void saveAlbums(List<Album> albums) {
        try (Writer writer = new FileWriter(ALBUMS_FILE)) {
            gson.toJson(albums, writer);
        } catch (IOException e) {
            System.out.println("❌ Failed to save albums: " + e.getMessage());
        }
    }

    public static List<Album> loadAlbums() {
        try (Reader reader = new FileReader(ALBUMS_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Album>>(){}.getType());
        } catch (IOException e) {
            System.out.println("📂 No saved albums found.");
            return new ArrayList<>();
        }
    }
}
