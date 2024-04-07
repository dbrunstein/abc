package com.example.bookapp.ui.comments;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ui.authors.OnItemClickListener;
import com.example.bookapp.ui.home.Book;
import com.example.bookapp.ui.home.BookViewHolder;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter{

    private List<Comment> comments;
    OnItemClickListener clickListener;
    public CommentAdapter(List<Comment> comments){

        this.clickListener = clickListener;
        this.comments = comments;
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
        ((CommentViewHolder) viewHolder).getUsername().setText(comments.get(i).getUserName());
        ((CommentViewHolder) viewHolder).getContent().setText(comments.get(i).getContent());
        /*
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_book_details);
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("bookId", id);
                editor.apply();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
