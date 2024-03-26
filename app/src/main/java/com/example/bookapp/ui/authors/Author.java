package com.example.bookapp.ui.authors;

public class Author {
    private int id;
    private String lastname;
    private String firstname;

    public Author(int id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }
}
