package com.example.bookapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.bookapp.R;
import com.example.bookapp.ViewModel;
import com.example.bookapp.ui.authors.Author;


public class BookDetailsFragment extends Fragment {


    public BookDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
            int id = sharedPreferences.getInt("bookId", 0);
            Book book = viewModel.getBook(id);
            TextView titleView = getView().findViewById(R.id.bookTitle);
            TextView dateView = getView().findViewById(R.id.bookDate);
            TextView authorView = getView().findViewById(R.id.bookAuthor);
            if(book != null){
                titleView.setText("Book "+book.getTitle());
                dateView.setText("Release : " + book.getDate());

                Author author = book.getAuthor();
                Log.d("author", author.getFirstname()+" "+author.getLastname());

                if(author != null)
                    authorView.append("\n"+author.getFirstname()+" "+author.getLastname());
                else
                    Log.d("erreur", "author not found");
            }

            else{
             Log.d("error", "erreur");
            }

        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }
}