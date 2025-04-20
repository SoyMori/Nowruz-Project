package com.genius.model;

import com.genius.account.Artist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Song {
    private String title;
    private Artist mainArtist;
    private String lyrics;
    private String genre;
    private LocalDate releaseDate;
    private int views;
    private List<String> tags;
    private List<Comment> comments;



    public Song(String title, Artist mainArtist, String lyrics, String genre, LocalDate releaseDate) {
        this.title = title;
        this.mainArtist = mainArtist;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.views = 0;
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void incrementViews() {
        views++;
    }

    public int getViews() {
        return views;
    }

    public String getTitle() {
        return title;
    }

    public Artist getMainArtist() {
        return mainArtist;
    }

    public String getGenre() {
        return genre;
    }

    public String getLyrics() {
        return lyrics;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return title + " by " + mainArtist.getUsername() + " | Genre: " + genre + " | Released: " + releaseDate + " | Views: " + views;
    }
}
