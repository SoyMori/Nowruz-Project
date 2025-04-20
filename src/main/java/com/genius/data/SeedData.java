package com.genius.data;

import com.genius.account.*;
import com.genius.model.*;
import com.genius.service.*;

import java.time.LocalDate;
import java.util.List;

public class SeedData {

    public static void initialize(List<Account> accounts,
                                  SongService songService,
                                  AlbumService albumService) {


        Artist artist1 = new Artist("Mehrad", 40, "mehrad@email.com", "hidden", "hide");
        Artist artist2 = new Artist("shayea", 32, "shayea@email.com", "shayea", "1234");


        User user1 = new User("Ali", 20, "ali@gmail.com", "ali20", "pass1");
        User user2 = new User("Sara", 22, "sara@gmail.com", "sara22", "pass2");

        accounts.add(artist1);
        accounts.add(artist2);
        accounts.add(user1);
        accounts.add(user2);

        Song s1 = new Song("Mano Bepar", artist1, "mano bespar mano bespar be gozashte...", "Pop", LocalDate.of(2022, 5, 15));
        Song s2 = new Song("Dele Man", artist1, "dele man tange baraye hame roozayi ke raftan...", "Pop", LocalDate.of(2022, 6, 12));
        s1.addTag("love");
        s2.addTag("sad");

        Song s3 = new Song("Bekhand", artist2, "bekhand  az farda bikhabar ye modat boro tanhayi safar...", "Rap", LocalDate.of(2023, 12, 21));

        songService.getAllSongs().addAll(List.of(s1, s2, s3));

        Album album1 = new Album("pizza", artist1, LocalDate.of(2022, 6, 20));
        album1.addSong(s1);
        album1.addSong(s2);

        albumService.getAllAlbums().add(album1);

        user1.followArtist(artist1);
        user2.followArtist(artist2);
    }
}
