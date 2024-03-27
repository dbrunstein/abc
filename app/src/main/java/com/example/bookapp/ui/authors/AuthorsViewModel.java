package com.example.bookapp.ui.authors;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import org.json.JSONObject;

import java.util.List;

public class AuthorsViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Author>> mText;

    private APIRequest apiRequest = new APIRequest();


    public AuthorsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());
        JsonArrayRequest getRequest = apiRequest.get(mText);
        queue.add(getRequest);


        //mText.setValue("This is author fragment");
    }

    public LiveData<List<Author>> getAuthors() {
        return mText;
    }

    public Author getAuthor(int id){
        for(Author author : mText.getValue()){
            if(author.getId() == id){
                return author;
            }
        }
        return null;
    }

}