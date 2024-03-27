package com.example.bookapp.ui.authors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import com.example.bookapp.ui.home.Book;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter {

    private List<Author> mData;
    private OnItemClickListener clickListener;

    public AuthorAdapter(List<Author> mData) {
        this.mData = mData;
        this.clickListener = clickListener;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
        return new AuthorViewHolder(view, clickListener);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        String firstname = mData.get(position).getFirstname();
        String lastname = mData.get(position).getLastname();
        List<Book> books = mData.get(position).getBooks();
        int id = mData.get(position).getId();
        ((AuthorViewHolder) holder).getFirstname().setText("Firstname : "+firstname);
        ((AuthorViewHolder) holder).getLastname().setText("Lastname : "+lastname);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_author_details);
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("author", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("authorId", id);
                editor.apply();

            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }



}
