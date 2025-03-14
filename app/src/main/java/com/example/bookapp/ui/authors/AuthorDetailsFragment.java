package com.example.bookapp.ui.authors;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import com.example.bookapp.ViewModel;
import com.example.bookapp.R;
import com.example.bookapp.model.Author;
import com.example.bookapp.model.Book;
import com.example.bookapp.ui.books.BookAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class AuthorDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuthorDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_author_details, container, false);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.author_details_recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        viewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("author", Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt("authorId", 0);
                Author author = viewModel.getAuthor(id);
            Button delete = rootView.findViewById(R.id.delete_author);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RequestQueue deleteQueue = Volley.newRequestQueue(getContext());
                    APIRequest apiRequest = new APIRequest();
                    StringRequest deleteRequest = apiRequest.deleteAuthor(id);
                    deleteQueue.add(deleteRequest);
                    Snackbar.make(view, "Author Deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Navigation.findNavController(view).navigate(R.id.navigation_dashboard);
                }
            });

            TextView txt = rootView.findViewById(R.id.data);
            List<Book> books = author.getBooks();
            BookAdapter mAdapter = new BookAdapter(books);
            mRecyclerView.setAdapter(mAdapter);
            txt.setText(author.getFirstname()+" "+author.getLastname()+"\n\nListe des livre :\n\n");
            if(books.size()==0){
                txt.append("Book not found");
            }

        });
        return rootView;
    }
}