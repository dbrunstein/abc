package com.example.bookapp.ui.home;

import com.example.bookapp.ui.authors.Author;

import java.util.ArrayList;


public class Book {

    private int id;
    private String title;
    private Author author;
    private int date;
    private ArrayList<Tag> tags;
    //private String description;
    //private double rating;
    //private ArrayList<Tag> tags;
    //private ArrayList<Comment> comments;

    public Book(int id, String title, Author author,int date){
        this.title = title;
        this.id = id;
        this.author = author;
        this.date = date;
        this.tags = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    public Author getAuthor() {
        return author;
    }
    public int getDate() {
        return date;
    }
    public ArrayList<Tag> getTags(){
        return tags;
    }
    public void setTags(ArrayList<Tag> tags){
        this.tags = tags;
    }

    public void setAuthor(Author auth){
        author = auth;
    }
    /*
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
    }*/

}
