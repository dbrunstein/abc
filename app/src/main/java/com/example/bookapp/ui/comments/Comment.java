package com.example.bookapp.ui.comments;

import java.sql.Date;
import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
    public Comment(int id, String content, String userName){
        this.id = id;
        this.content = content;
        this.createdAt = null;
        this.updatedAt = null;
        this.userName = userName;
    }
    public Comment(int id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String userName){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
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
    public int getId() {
        return id;
    }
}
