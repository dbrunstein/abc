package com.example.bookapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Book> books; // faire une class book ou est convertit les
                                                    // livre en objet java

    public HomeViewModel() throws JSONException {
        books = new MutableLiveData<>();
        //mText.setValue("WELCOME TO THE BOOK ZONE!\n ONLY BOOKS IN ANIME MOLOCH");
        //books.setValue(new JSONArray("[{\"moloch\":666,}]"));
        books.setValue(new Book(1,"test"));
    }

    public LiveData<Book> getBooks() {
        return books;
    }
}