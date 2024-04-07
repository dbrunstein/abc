package com.example.bookapp.ui.comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.model.Comment;
import com.example.bookapp.ui.authors.OnItemClickListener;

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
                .inflate(R.layout.comment_item, viewGroup, false);
        return new CommentViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CommentViewHolder) viewHolder).getUsername().setText(comments.get(i).getUserName());
        ((CommentViewHolder) viewHolder).getContent().setText(comments.get(i).getContent());
        ((CommentViewHolder) viewHolder).getRatingBar().setRating((float) comments.get(i).getRating());


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
