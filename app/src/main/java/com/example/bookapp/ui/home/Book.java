package com.example.bookapp.ui.home;

import com.example.bookapp.ui.authors.Author;

import java.util.ArrayList;

public class Book {

    private Author author;
    private String description;
    private double rating;
    private ArrayList<Tag> tags;
    private ArrayList<Comment> comments;

    public Book(){
        description = "MOULOUDE LA FAMILLE WESH";
    }

    public void setAuthor(Author auth){
        author = auth;
    }
    public void setDescription(String desc){
        description = desc;
    }
    public void setRating(Double rate){
        rating = rate;
    }
    public void setTags(ArrayList<Tag> givenTag){
        tags = givenTag;
    }
    public void setComments(ArrayList<Comment> givenComments){
        comments = givenComments;
    }

}
