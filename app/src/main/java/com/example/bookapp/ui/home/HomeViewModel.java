package com.example.bookapp.ui.home;

import android.app.Application;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import com.example.bookapp.ui.authors.Author;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Book>> books; // faire une class book ou est convertit les
                                                    // livre en objet java

    public HomeViewModel(Application application) throws JSONException {
        super(application);
        books = new MutableLiveData<>();
        ArrayList<Book> testList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());
        APIRequest apiRequest = new APIRequest();
        JsonArrayRequest getRequest = apiRequest.getBooks(books);
        queue.add(getRequest);
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public Book getBook(int id){
        for(Book book : books.getValue()){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

}