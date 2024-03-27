package com.example.bookapp.ui.authors;

import com.example.bookapp.ui.home.Book;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String lastname;
    private String firstname;

    private List<Book> books;

    public Author(int id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.books = new ArrayList<>();
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

    public List<Book> getBooks() {
        return books;
    }
}
