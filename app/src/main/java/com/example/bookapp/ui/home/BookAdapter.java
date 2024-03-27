package com.example.bookapp.ui.home;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.ui.authors.Author;
import org.json.JSONArray;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter{

    private List<Book> books;

    public BookAdapter(List<Book> books, Context con){
        this.books = books;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
