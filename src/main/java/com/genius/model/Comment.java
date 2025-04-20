package com.genius.model;

import com.genius.account.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private String text;
    private Account commenter;
    private LocalDateTime timestamp;
    private List<Comment> replies;
    private int likes;
    private int dislikes;

    public Comment(String text, Account commenter) {
        this.text = text;
        this.commenter = commenter;
        this.timestamp = LocalDateTime.now();
        this.replies = new ArrayList<>();
        this.likes = 0;
        this.dislikes = 0;
    }

    public void addReply(Comment reply) {
        replies.add(reply);
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }

    public String getText() {
        return text;
    }

    public Account getCommenter() {
        return commenter;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void displayWithReplies(String prefix) {
        System.out.println(prefix + commenter.getUsername() + ": " + text + " ❤️ " + likes + " 👎 " + dislikes);
        for (Comment reply : replies) {
            reply.displayWithReplies(prefix + "  ↳ ");
        }
    }

    @Override
    public String toString() {
        return commenter.getUsername() + ": " + text + " (" + timestamp + ")";
    }
}
