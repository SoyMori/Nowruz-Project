package com.genius.service;

import com.genius.account.Account;
import com.genius.account.Artist;
import com.genius.account.User;
import com.genius.model.Comment;
import com.genius.model.Song;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SongService {
    private List<Song> songs;

    public SongService() {
        this.songs = new ArrayList<>();
    }

    public SongService(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getAllSongs() {
        return songs;
    }

    public void createSong(Artist artist) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter lyrics: ");
        String lyrics = scanner.nextLine();
        System.out.print("Enter release date: ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        Song song = new Song(title, artist, lyrics, genre, releaseDate);
        artist.addSong(song);
        songs.add(song);

        for (User follower : artist.getFollowers()) {
            follower.addNotification("🎵 " + artist.getUsername() + " released a new song: \"" + title + "\"");
        }

        System.out.println("✅ Song added successfully.");
    }

    public void showAllSongs() {
        System.out.println("\n🎧 List of All Songs:");
        if (songs.isEmpty()) {
            System.out.println("❌ No songs available.");
            return;
        }

        int index = 1;
        for (Song song : songs) {
            System.out.println(index + ". " + song.toString());
            index++;
        }
    }

    public void viewSongDetails(Account viewer) {
        showAllSongs();
        if (songs.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song number to view details: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > songs.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Song selectedSong = songs.get(choice - 1);
        selectedSong.incrementViews();

        System.out.println("\n🎵 Song Details:");
        System.out.println("Title: " + selectedSong.getTitle());
        System.out.println("Artist: " + selectedSong.getMainArtist().getUsername());
        System.out.println("Genre: " + selectedSong.getGenre());
        System.out.println("Release Date: " + selectedSong.getReleaseDate());
        System.out.println("Views: " + selectedSong.getViews());
        System.out.println("Lyrics:\n" + selectedSong.getLyrics());

        if (viewer instanceof User user) {
            user.addToHistory(selectedSong);
        }
    }

    public void addCommentToSong(Account commenter) {
        showAllSongs();
        if (songs.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song number to comment on: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > songs.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Song selectedSong = songs.get(choice - 1);
        System.out.print("Write your comment: ");
        String text = scanner.nextLine();

        Comment comment = new Comment(text, commenter);
        selectedSong.addComment(comment);

        System.out.println("✅ Comment added.");
    }

    public void showCommentsOfSong() {
        showAllSongs();
        if (songs.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song number to view comments: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > songs.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Song selectedSong = songs.get(choice - 1);
        List<Comment> comments = selectedSong.getComments();

        if (comments.isEmpty()) {
            System.out.println("🕳️ No comments yet.");
        } else {
            System.out.println("\n💬 Comments:");
            for (int i = 0; i < comments.size(); i++) {
                System.out.print((i + 1) + ". ");
                comments.get(i).displayWithReplies("   ");
            }
        }
    }

    public void voteCommentOnSong() {
        showAllSongs();
        if (songs.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song number: ");
        int songIndex = Integer.parseInt(scanner.nextLine());

        if (songIndex < 1 || songIndex > songs.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Song song = songs.get(songIndex - 1);
        List<Comment> comments = song.getComments();

        if (comments.isEmpty()) {
            System.out.println("❌ No comments to vote on.");
            return;
        }

        for (int i = 0; i < comments.size(); i++) {
            System.out.print((i + 1) + ". ");
            comments.get(i).displayWithReplies("   ");
        }

        System.out.print("Choose comment number to vote on: ");
        int commentIndex = Integer.parseInt(scanner.nextLine());

        if (commentIndex < 1 || commentIndex > comments.size()) {
            System.out.println("❌ Invalid comment.");
            return;
        }

        Comment selectedComment = comments.get(commentIndex - 1);

        System.out.println("1. 👍 Like\n2. 👎 Dislike");
        String vote = scanner.nextLine();

        if (vote.equals("1")) {
            selectedComment.like();
            System.out.println("✅ You liked the comment.");
        } else if (vote.equals("2")) {
            selectedComment.dislike();
            System.out.println("✅ You disliked the comment.");
        } else {
            System.out.println("❌ Invalid option.");
        }
    }

    public void replyToCommentOnSong(Account replier) {
        showAllSongs();
        if (songs.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song number: ");
        int songIndex = Integer.parseInt(scanner.nextLine());

        if (songIndex < 1 || songIndex > songs.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Song song = songs.get(songIndex - 1);
        List<Comment> comments = song.getComments();

        if (comments.isEmpty()) {
            System.out.println("❌ No comments to reply to.");
            return;
        }

        for (int i = 0; i < comments.size(); i++) {
            System.out.print((i + 1) + ". ");
            comments.get(i).displayWithReplies("   ");
        }

        System.out.print("Choose comment number to reply to: ");
        int commentIndex = Integer.parseInt(scanner.nextLine());

        if (commentIndex < 1 || commentIndex > comments.size()) {
            System.out.println("❌ Invalid comment.");
            return;
        }

        Comment parent = comments.get(commentIndex - 1);
        System.out.print("Write your reply: ");
        String replyText = scanner.nextLine();

        Comment reply = new Comment(replyText, replier);
        parent.addReply(reply);

        Account owner = parent.getCommenter();
        if (!owner.getUsername().equals(replier.getUsername()) && owner instanceof User user) {
            user.addNotification("💬 " + replier.getUsername() + " replied to your comment on \"" + song.getTitle() + "\"");
        }

        System.out.println("✅ Reply added.");
    }

    public void showTopSongs(int limit) {
        if (songs.isEmpty()) {
            System.out.println("❌ No songs available.");
            return;
        }

        System.out.println("\n📊 Top " + limit + " Most Viewed Songs:");

        List<Song> sorted = new ArrayList<>(songs);
        sorted.sort((s1, s2) -> Integer.compare(s2.getViews(), s1.getViews()));

        int count = Math.min(limit, sorted.size());
        for (int i = 0; i < count; i++) {
            Song song = sorted.get(i);
            System.out.println((i + 1) + ". " + song.getTitle()
                    + " by " + song.getMainArtist().getUsername()
                    + " | Views: " + song.getViews()
                    + " " + generateStarRating(song.getViews()));
        }
    }

    public void showTopSongsByArtist(Artist artist, int limit) {
        List<Song> artistSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getMainArtist().equals(artist)) {
                artistSongs.add(song);
            }
        }

        if (artistSongs.isEmpty()) {
            System.out.println("🕳️ You have no songs yet.");
            return;
        }

        artistSongs.sort((s1, s2) -> Integer.compare(s2.getViews(), s1.getViews()));

        System.out.println("\n📊 Your Top " + limit + " Songs:");
        int count = Math.min(limit, artistSongs.size());
        for (int i = 0; i < count; i++) {
            Song s = artistSongs.get(i);
            System.out.println((i + 1) + ". " + s.getTitle() + " | Views: " + s.getViews()
                    + " " + generateStarRating(s.getViews()));
        }
    }

    private String generateStarRating(int views) {
        if (views >= 100) return "★★★★★";
        else if (views >= 70) return "★★★★☆";
        else if (views >= 40) return "★★★☆☆";
        else if (views >= 10) return "★★☆☆☆";
        else if (views >= 1)  return "★☆☆☆☆";
        else return "☆☆☆☆☆";
    }

    public void search(List<Account> accounts, List<com.genius.model.Album> albums) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("🔍 Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;

        System.out.println("\n🎵 Songs matching \"" + keyword + "\":");
        boolean songFound = false;
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyword)) {
                System.out.println("- " + song);
                songFound = true;
                found = true;
            }
        }
        if (!songFound) System.out.println("(no results)");

        System.out.println("\n📀 Albums matching \"" + keyword + "\":");
        boolean albumFound = false;
        for (com.genius.model.Album album : albums) {
            if (album.getTitle().toLowerCase().contains(keyword)) {
                System.out.println("- " + album);
                albumFound = true;
                found = true;
            }
        }
        if (!albumFound) System.out.println("(no results)");

        System.out.println("\n🎤 Artists matching \"" + keyword + "\":");
        boolean artistFound = false;
        for (Account acc : accounts) {
            if (acc instanceof Artist && acc.getUsername().toLowerCase().contains(keyword)) {
                System.out.println("- " + acc.getUsername());
                artistFound = true;
                found = true;
            }
        }
        if (!artistFound) System.out.println("(no results)");

        if (!found) {
            System.out.println("\n🔎 No matching result in any category.");
        }
    }
}
