package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Photo {
    private int id;
    private int userId;
    private String caption;
    private String photoUrl;
    private LocalDateTime datePublished;

    private boolean isPrivate;

    private ArrayList<CommentDto> comments;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    public ArrayList<CommentDto> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentDto> comments) {
        this.comments = comments;
    }
}