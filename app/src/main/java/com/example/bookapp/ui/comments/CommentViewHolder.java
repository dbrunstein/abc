package com.example.bookapp.ui.comments;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ui.authors.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView userName;
    private TextView content;
    private RatingBar ratingBar;
    private OnItemClickListener clickListener;
    public CommentViewHolder(@NonNull @NotNull View itemView, OnItemClickListener listener) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
        userName = itemView.findViewById(R.id.userName);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5); // nombre d'étoiles affichées
        this.clickListener = listener;
        itemView.setOnClickListener(this);
    }
    public TextView getContent() {
        return content;
    }
    public TextView getUsername() {
        return userName;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    @Override
    public void onClick(View view) {
        if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
}
