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
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.authors.AuthorsViewModel;

import java.util.List;


public class BookDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            //if(savedInstanceState != null){
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
            int id = sharedPreferences.getInt("bookId", 0);
            Book book = viewModel.getBook(id);
            TextView txt = getView().findViewById(R.id.book_details_txt);
            if(book != null){
                txt.setText("Book "+book.getTitle());
                Author author = book.getAuthor();
                //Log.d("erreur", author.getFirstname());
                if(author != null)
                    txt.append("\n"+author.getFirstname()+" "+author.getLastname());
                else
                    Log.d("erreur", "author not found");
            }



            // }
            //else{
            //  Log.d("error", "erreur");
            //}

        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }
}