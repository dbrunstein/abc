package com.example.bookapp.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Book>> books; // faire une class book ou est convertit les
                                                    // livre en objet java
    private APIRequest apiRequest = new APIRequest();
    public HomeViewModel(@NonNull Application application) throws JSONException {
        books = new MutableLiveData<>();
        //mText.setValue("WELCOME TO THE BOOK ZONE!\n ONLY BOOKS IN ANIME MOLOCH");
        //books.setValue(new JSONArray("[{\"moloch\":666,}]"));
        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());
        JsonArrayRequest getRequest = apiRequest.getBooks(books);
        queue.add(getRequest);
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
}