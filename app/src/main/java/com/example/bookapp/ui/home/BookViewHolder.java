package com.example.bookapp.ui.home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;

import org.jetbrains.annotations.NotNull;

public class BookViewHolder extends RecyclerView.ViewHolder{
    private TextView title;
    public BookViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }

    public TextView getTitle() {
        return title;
    }

}
