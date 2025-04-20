package com.genius;

import com.genius.account.*;
import com.genius.data.FileManager;
import com.genius.data.SeedData;
import com.genius.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Account> accounts = FileManager.loadAccounts();
        List<com.genius.model.Song> songs = FileManager.loadSongs();
        List<com.genius.model.Album> albums = FileManager.loadAlbums();

        AuthService authService = new AuthService(accounts); // پاس دادن لیست به AuthService
        SongService songService = new SongService(songs);
        AlbumService albumService = new AlbumService(albums);

        if (accounts.isEmpty() && songs.isEmpty() && albums.isEmpty()) {
            System.out.println("📌 No existing data found. Initializing seed data...");
            SeedData.initialize(accounts, songService, albumService);
        }


        Account currentAccount = null;

        while (true) {
            System.out.println("\n🎵 Welcome to Genius CLI 🎵");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> currentAccount = authService.login();
                case "2" -> currentAccount = authService.register();
                case "0" -> {
                    FileManager.saveAccounts(accounts);
                    FileManager.saveSongs(songService.getAllSongs());
                    FileManager.saveAlbums(albumService.getAllAlbums());
                    System.out.println("👋 Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }

            if (currentAccount != null) {
                if (currentAccount instanceof User user) {
                    while (true) {
                        System.out.println("\n👤 User Menu:");
                        System.out.println("1. View followed artists");
                        System.out.println("2. Follow an artist");
                        System.out.println("3. View all songs");
                        System.out.println("4. View song details");
                        System.out.println("5. Add comment to a song");
                        System.out.println("6. View comments of a song");
                        System.out.println("7. Search song, album, or artist");
                        System.out.println("8. View Top Songs");
                        System.out.println("9. Vote on a comment (like/dislike)");
                        System.out.println("10. View notifications");
                        System.out.println("11. View history");
                        System.out.println("12. Reply to a comment");
                        System.out.println("0. Logout");

                        System.out.print("Select option: ");
                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> user.listFollowedArtists();
                            case "2" -> {
                                System.out.print("Enter artist username to follow: ");
                                String artistUsername = scanner.nextLine();
                                boolean found = false;
                                for (Account acc : accounts) {
                                    if (acc instanceof Artist artist && artist.getUsername().equals(artistUsername)) {
                                        user.followArtist(artist);
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) System.out.println("❌ Artist not found.");
                            }
                            case "3" -> songService.showAllSongs();
                            case "4" -> songService.viewSongDetails(user);
                            case "5" -> songService.addCommentToSong(user);
                            case "6" -> songService.showCommentsOfSong();
                            case "7" -> songService.search(accounts, albumService.getAllAlbums());
                            case "8" -> songService.showTopSongs(5);
                            case "9" -> songService.voteCommentOnSong();
                            case "10" -> user.showNotifications();
                            case "11" -> user.showHistory();
                            case "12" -> songService.replyToCommentOnSong(user);
                            case "0" -> {
                                currentAccount = null;
                                System.out.println("👋 Logged out.");
                            }
                            default -> System.out.println("❌ Invalid option.");
                        }

                        if (currentAccount == null) break;
                    }

                } else if (currentAccount instanceof Artist artist) {
                    while (true) {
                        System.out.println("\n🎤 Artist Menu:");
                        System.out.println("1. View profile");
                        System.out.println("2. Create new song");
                        System.out.println("3. Create album");
                        System.out.println("4. View your top songs");
                        System.out.println("0. Logout");

                        System.out.print("Select option: ");
                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> artist.showProfile();
                            case "2" -> songService.createSong(artist);
                            case "3" -> albumService.createAlbum(artist, songService);
                            case "4" -> songService.showTopSongsByArtist(artist, 5);
                            case "0" -> {
                                currentAccount = null;
                                System.out.println("👋 Logged out.");
                            }
                            default -> System.out.println("❌ Invalid option.");
                        }

                        if (currentAccount == null) break;
                    }

                } else if (currentAccount instanceof Admin) {
                    while (true) {
                        System.out.println("\n🔧 Admin Menu:");
                        System.out.println("1. View profile");
                        System.out.println("2. Approve pending artists");
                        System.out.println("0. Logout");

                        System.out.print("Select option: ");
                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> currentAccount.showProfile();
                            case "2" -> authService.approveArtists();
                            case "0" -> {
                                currentAccount = null;
                                System.out.println("👋 Logged out.");
                            }
                            default -> System.out.println("❌ Invalid option.");
                        }

                        if (currentAccount == null) break;
                    }
                }
            }
        }
    }
}
