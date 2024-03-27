package com.example.bookapp.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.authors.AuthorViewHolder;

import org.json.JSONArray;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter{

    private List<Book> books;
    public BookAdapter(List<Book> books){
        this.books = books;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item, viewGroup, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("test","bonjour");
        ((BookViewHolder) viewHolder).getTitle().setText(books.get(i).getTitle());
        //Log.d("test", books.get(0).getTitle());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
