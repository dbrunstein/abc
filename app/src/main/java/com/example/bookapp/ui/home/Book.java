package com.example.bookapp.ui.home;

import com.example.bookapp.ui.authors.Author;

import java.util.ArrayList;

public class Book {

    int id;
    String title;
    private Author author;
    private String description;
    private double rating;
    private ArrayList<Tag> tags;
    private ArrayList<Comment> comments;

    public Book(int id, String title){
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
