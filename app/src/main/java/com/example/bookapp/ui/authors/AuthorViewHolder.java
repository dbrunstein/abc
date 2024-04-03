package com.example.bookapp.ui.authors;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import org.jetbrains.annotations.NotNull;



public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView name;

    private OnItemClickListener clickListener;

    public AuthorViewHolder(@NonNull @NotNull View itemView, OnItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        this.clickListener = listener;
        itemView.setOnClickListener(this);
    }

    public TextView getName() {
        return name;
    }



    @Override
    public void onClick(View view) {
        if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
}
