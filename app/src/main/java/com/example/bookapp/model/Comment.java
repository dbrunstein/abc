package com.example.bookapp.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
    private double rating;
    public Comment(int id, String content, String userName){
        this.id = id;
        this.content = content;
        this.createdAt = null;
        this.updatedAt = null;
        this.userName = userName;
        this.rating = 5.0;
    }
    public Comment(int id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String userName){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
        this.rating = 5.0;
    }
    public String getContent(){
        return content;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public String getUserName() {
        return userName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }
}
