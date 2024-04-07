package com.example.bookapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import com.example.bookapp.R;
import com.example.bookapp.ViewModel;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.comments.CommentAdapter;
import com.google.android.material.snackbar.Snackbar;


public class BookDetailsFragment extends Fragment {

    APIRequest apiRequest;
    RequestQueue RequestQueue;
    public BookDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_details, container, false);
        this.apiRequest = new APIRequest();
        this.RequestQueue = Volley.newRequestQueue(getContext());
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("bookId", 0);
        TextView titleView = root.findViewById(R.id.bookTitle);
        TextView dateView = root.findViewById(R.id.bookDate);
        TextView authorView = root.findViewById(R.id.bookAuthor);
        TextView tagView = root.findViewById(R.id.bookTags);
        Button delete_book = root.findViewById(R.id.delete_book);
        RatingBar ratingBar = root.findViewById(R.id.ratingBarMean);
        RecyclerView mRecyclerView = root.findViewById(R.id.commentRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            Book book = viewModel.getBook(id);
            viewModel.fetchBooksWithTags(book);
            viewModel.fetchCommentsWithBookId(book);
            viewModel.fetchRatingWithBookId(book);
            if (book != null) {

                titleView.setText("Title : " + book.getTitle());
                dateView.setText("Release Year : " + book.getDate());
                ratingBar.setRating((float)book.getRating());

                Author author = book.getAuthor();
                StringBuilder allTags = new StringBuilder();


                for (Tag tag : book.getTags()) {
                    allTags.append(",").append(tag.getName());
                }
                tagView.setText(allTags.toString());


                dateView.setText("Release Year : " + book.getDate());

                if (author != null)
                    authorView.setText("Author : " + author.getFirstname() + " " + author.getLastname());
                else
                    Log.d("erreur", "author not found");
                }
                CommentAdapter mAdapter = new CommentAdapter(book.getComments());
                mRecyclerView.setAdapter(mAdapter);
        });

            delete_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringRequest deleteRequest = apiRequest.deleteBook(id);
                    RequestQueue.add(deleteRequest);
                    Snackbar.make(view, "Book Deleted", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    Navigation.findNavController(view).navigate(R.id.navigation_home);
                }
            });


        // Inflate the layout for this fragment
        return root;
    }


}