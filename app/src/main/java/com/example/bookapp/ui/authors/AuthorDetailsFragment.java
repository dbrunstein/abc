package com.example.bookapp.ui.authors;

import android.content.ClipData;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.R;
import com.example.bookapp.ui.home.Book;
import org.json.JSONArray;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuthorDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AuthorsViewModel viewModel = new ViewModelProvider(this).get(AuthorsViewModel.class);
        viewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
            //if(savedInstanceState != null){
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("author", Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt("authorId", 0);
                Author author = viewModel.getAuthor(id);
                TextView txt = getView().findViewById(R.id.data);
                List<Book> books = author.getBooks();
                txt.setText(author.getFirstname()+" "+author.getLastname());
                for(Book book : books){
                    txt.append("\n"+book.getTitle());
                }


           // }
            //else{
              //  Log.d("error", "erreur");
            //}

        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_details, container, false);
    }
}