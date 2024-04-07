package com.example.bookapp.ui.home;

public class Tag {
    private String name;
    private int id;
    public Tag( int id, String name){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return name;
    }
}
