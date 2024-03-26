package com.example.bookapp.ui.authors;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import org.jetbrains.annotations.NotNull;



public class AuthorViewHolder extends RecyclerView.ViewHolder {

    private TextView firstname;
    private TextView lastname;

    public AuthorViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        firstname = itemView.findViewById(R.id.firstname);
        lastname = itemView.findViewById(R.id.lastname);
    }

    public TextView getFirstname() {
        return firstname;
    }

    public TextView getLastname() {
        return lastname;
    }
}
