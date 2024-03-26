package com.example.bookapp.ui.authors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter {

    private List<Author> mData;

    public AuthorAdapter(List<Author> mData) {
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
        return new AuthorViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        ((AuthorViewHolder) holder).getFirstname().setText("Firstname : "+mData.get(position).getFirstname());
        ((AuthorViewHolder) holder).getLastname().setText("Lastname : "+mData.get(position).getLastname());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


}
