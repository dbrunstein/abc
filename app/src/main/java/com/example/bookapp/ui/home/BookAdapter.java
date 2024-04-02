package com.example.bookapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.authors.AuthorViewHolder;

import com.example.bookapp.ui.authors.OnItemClickListener;
import org.json.JSONArray;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter{

    private List<Book> books;
    OnItemClickListener clickListener;
    public BookAdapter(List<Book> books){

        this.clickListener = clickListener;
        this.books = books;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item, viewGroup, false);
        return new BookViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BookViewHolder) viewHolder).getTitle().setText(books.get(i).getTitle());
        int id = books.get(i).getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_book_details);
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("bookId", id);
                editor.apply();

            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
