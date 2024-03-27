package com.example.bookapp.ui.authors;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import org.jetbrains.annotations.NotNull;



public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView firstname;
    private TextView lastname;

    private OnItemClickListener clickListener;

    public AuthorViewHolder(@NonNull @NotNull View itemView, OnItemClickListener listener) {
        super(itemView);
        firstname = itemView.findViewById(R.id.firstname);
        lastname = itemView.findViewById(R.id.lastname);
        this.clickListener = listener;
        itemView.setOnClickListener(this);
    }

    public TextView getFirstname() {
        return firstname;
    }

    public TextView getLastname() {
        return lastname;
    }

    @Override
    public void onClick(View view) {
        if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
}
