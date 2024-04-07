package com.example.bookapp.model;

import java.util.ArrayList;


public class Book {

    private int id;
    private String title;
    private Author author;
    private int date;
    private ArrayList<Tag> tags;
    //private String description;
    private double rating;
    private ArrayList<Comment> comments;

    public Book(int id, String title, Author author,int date,ArrayList<Tag> tags, ArrayList<Comment> comments){
        this.title = title;
        this.id = id;
        this.author = author;
        this.date = date;
        this.tags = tags;
        this.comments = comments;
    }
    public Book(int id, String title, Author author,int date, ArrayList<Comment> comments){
        this.title = title;
        this.id = id;
        this.author = author;
        this.date = date;
        this.tags = new ArrayList<>();
        this.comments = comments;
    }
    public Book(int id, String title, Author author,int date){
        this.title = title;
        this.id = id;
        this.author = author;
        this.date = date;
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
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
    public ArrayList<Comment>getComments(){
        return comments;
    }

    public double getRating() {
        return rating;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setRating(double rating) {
        this.rating = rating;
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
