package com.example.bookapp.ui.books;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;

import com.example.bookapp.ui.authors.OnItemClickListener;
import org.jetbrains.annotations.NotNull;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView title;
    private OnItemClickListener clickListener;
    public BookViewHolder(@NonNull @NotNull View itemView, OnItemClickListener listener) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        this.clickListener = listener;
        itemView.setOnClickListener(this);
    }

    public TextView getTitle() {
        return title;
    }

    @Override
    public void onClick(View view) {
        if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
}
