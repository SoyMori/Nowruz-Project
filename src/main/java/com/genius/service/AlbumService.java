package com.genius.service;

import com.genius.account.Artist;
import com.genius.account.User;
import com.genius.model.Album;
import com.genius.model.Song;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AlbumService {
    private List<Album> albums;

    public AlbumService(List<Album> albums) {
        this.albums = albums;
    }

    public AlbumService() {
        this.albums = new java.util.ArrayList<>();
    }

    public List<Album> getAllAlbums() {
        return albums;
    }

    public void createAlbum(Artist artist, SongService songService) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();

        System.out.print("Enter release date (yyyy-mm-dd): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        Album album = new Album(title, artist, releaseDate);
        albums.add(album);

        System.out.print("How many songs to add to this album? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("🎵 Adding song " + (i + 1));
            songService.createSong(artist);
            Song last = songService.getAllSongs().get(songService.getAllSongs().size() - 1);
            album.addSong(last);
        }

        for (User follower : artist.getFollowers()) {
            follower.addNotification("📀 " + artist.getUsername() + " released a new album: \"" + title + "\"");
        }

        System.out.println("✅ Album created and saved.");
    }
}
