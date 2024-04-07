package com.example.bookapp;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.home.Book;
import com.example.bookapp.ui.comments.Comment;
import com.example.bookapp.ui.home.Tag;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Author>> authors;
    private final MutableLiveData<List<Book>> books;

    private final MutableLiveData<List<Tag>> tags;
    private final MutableLiveData<List<Comment>> comments;
    private APIRequest apiRequest = new APIRequest();
    private RequestQueue queue;


    public ViewModel(@NonNull Application application) {
        super(application);
        books = new MutableLiveData<>();
        authors = new MutableLiveData<>();
        tags = new MutableLiveData<>();
        queue = Volley.newRequestQueue(application.getApplicationContext());
        comments = new MutableLiveData<>();
        this.load_authors();
        this.load_books();
        this.load_tags();
        this.load_comments();
    }

    public LiveData<List<Author>> getAuthors() {
        return authors;
    }

    public Author getAuthor(int id){
        for(Author author : authors.getValue()){
            if(author.getId() == id){
                return author;
            }
        }
        return null;
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
    public LiveData<List<Tag>> getTags() {
        return tags;
    }
    public Book getBook(int id){
        for(Book book : books.getValue()){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    public void load_authors(){
        JsonArrayRequest getRequest = apiRequest.getAuthors(authors);
        Log.d("molochus", "load_authors: "+authors.getValue());
        queue.add(getRequest);
    }
    public void load_books(){
        JsonArrayRequest getBookRequest = apiRequest.getBooks(books);
        queue.add(getBookRequest);
    }
    public void load_tags(){
        JsonArrayRequest getTagsRequest = apiRequest.getTags(tags);
        queue.add(getTagsRequest);
    }
    public void load_comments(){
        JsonArrayRequest getCommentsRequest = apiRequest.getComments(comments);
        queue.add(getCommentsRequest);
    }
    public void fetchCommentsWithBookId(Book book){
        JsonArrayRequest getBookIdCommentsRequest = apiRequest.getCommentsOfBook(book);
        queue.add(getBookIdCommentsRequest);
    }

    public void fetchBooksWithTags(Book book) {
        JsonArrayRequest jsonArrayRequest = apiRequest.getTagsofBook(books, book);
        queue.add(jsonArrayRequest);
        // Supposons que vous ayez un mécanisme pour gérer la réponse de cette requête et mettre à jour les données


    }

}