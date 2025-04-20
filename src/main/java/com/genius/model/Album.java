package com.genius.model;

import com.genius.account.Artist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private String title;
    private Artist artist;
    private LocalDate releaseDate;
    private List<Song> trackList;

    public Album(String title, Artist artist, LocalDate releaseDate) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.trackList = new ArrayList<>();
    }

    public void addSong(Song song) {
        trackList.add(song);
    }

    public List<Song> getTrackList() {
        return trackList;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "🎵 Album: " + title + " by " + artist.getUsername() + " | Released: " + releaseDate;
    }
}
