# 🎧 Genius Platform - Music Sharing CLI App

Welcome to the **Genius Platform** — a fully object-oriented, console-based Java application inspired by real-world music sharing platforms like Genius and Spotify.

This project was developed as part of an Advanced Programming course assignment. While the final presentation was canceled, this README showcases all the features completed, including **mandatory** and **bonus** features.

---

## 🚀 Features

### ✅ Core Features (Mandatory)
- **Account Management**
    - Register as `User`, `Artist`, or `Admin`
    - Login and Logout with credential validation
    - Admin approval required for new artist accounts

- **User Role Capabilities**
    - Follow artists and view followed list
    - View all available songs
    - View song details (lyrics, views, etc.)
    - Add and reply to comments
    - Like/dislike comments
    - Receive notifications (new songs, albums, replies)
    - View listening history

- **Artist Role Capabilities**
    - Create and release new songs
    - Create albums and add songs to them
    - View their top songs by view count
    - Notify followers when new content is released

- **Admin Role Capabilities**
    - View all pending artists
    - Approve or reject artist registrations

- **Song & Album System**
    - Songs have title, artist, lyrics, genre, release date
    - Albums contain multiple songs
    - Each song and album is linked to its artist

- **Comment System**
    - Nested replies
    - Voting system for comments (like/dislike)
    - Real-time notifications when replies are made

- **Search Functionality**
    - Search across songs, albums, and artist usernames
    - Case-insensitive, partial matches supported

- **Top Songs**
    - Global top songs (by views)
    - Artist-specific top songs

---

## 💾 Data Persistence

All application data is **automatically saved and loaded using JSON files**:

- `accounts.json` - Stores all users, artists, and admin accounts
- `songs.json` - Stores all created songs
- `albums.json` - Stores all created albums

The application uses **Gson** for serialization and deserialization, ensuring data remains intact across runs.

---

## 🌟 Bonus Features (Implemented)

- ✅ JSON-based persistent storage with `Gson`
- ✅ Notification system (with reply alerts)
- ✅ Comment replies and nested thread display
- ✅ Star-based visual representation for top songs
- ✅ Listening history for users
- ✅ Artist follower system with notifications
- ✅ Object-oriented design using inheritance and polymorphism
- ✅ Clean CLI interface with role-based menus
- ✅ Easy scalability to GUI (JavaFX-ready structure)

---

## 🧠 Object-Oriented Structure

- `Account` (abstract)
    - `User`
    - `Artist`
    - `Admin`
- `Song`
- `Album`
- `Comment`
- `AuthService`, `SongService`, `AlbumService`
- `FileManager` for persistence
- `Main.java` as the program entry point

---

## 🛠 Technologies Used

- Gradle
- Gson (Google JSON Library)
- IntelliJ IDEA

---

## 📁 Folder Structure


---

## ✅ How to Run

1. Clone or open the project in IntelliJ IDEA
2. Run the `Main` class
3. Interact through the console menus
4. Data will persist automatically via `.json` files

---

## 🤝 Author

> Developed by **morteza mahdavi**  
> Advanced Programming Course – 2025

---

## 📌 Final Note

Although the project presentation was canceled, this CLI application stands as a fully-functional, feature-rich music sharing platform with extensible architecture.

Thanks for checking it out! 🎸
