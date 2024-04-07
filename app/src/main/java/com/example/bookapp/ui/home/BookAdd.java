package com.example.bookapp.ui.home;

import android.os.Bundle;

import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import com.example.bookapp.R;
import com.example.bookapp.ViewModel;
import com.example.bookapp.ui.authors.Author;
import com.google.android.material.snackbar.Snackbar;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookAdd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookAdd extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookAdd() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book_add, container, false);


        RequestQueue queue = Volley.newRequestQueue(getContext());
        APIRequest apiRequest = new APIRequest();

        ArrayAdapter<CharSequence> author_adapter = ArrayAdapter.createFromResource(
                root.getContext(), R.array.author_array, android.R.layout.simple_spinner_item
        );

        ArrayList<String> authorListUser = new ArrayList<>();
        ArrayList<Author> authorList = new ArrayList<>();
        authorListUser.add("Select an author");



        // *** partie hand spinner ***
        Spinner authorSpinner = (Spinner) root.findViewById(R.id.select_author);
        // Create an ArrayAdapter using the string array and a default spinner layout.


        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        viewModel.getAuthors().observe(getViewLifecycleOwner(), authors ->{
            Log.d("author size", String.valueOf(authors.size()));
            for(Author author:authors){
                Log.d("s", "ajout"+author.getFirstname());
                authorList.add(author);
                authorListUser.add(author.getFirstname()+" "+author.getLastname());
            }
        });

        ArrayAdapter<String> authorAdapter = new ArrayAdapter(
                root.getContext(), android.R.layout.simple_spinner_item,authorListUser);
        // *** partie hand spinner ***
        authorSpinner.setAdapter(authorAdapter);


        Button addBook = root.findViewById(R.id.buttonBookAdd);

        addBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = ((Spinner) root.findViewById(R.id.select_author)).getSelectedItemPosition()-1;
                String title = ((EditText)root.findViewById(R.id.bookAddTitle)).getText().toString();
                String date = ((EditText)root.findViewById(R.id.bookAddDate)).getText().toString();
                if(position>=0){
                    int authorId = authorList.get(position).getId();
                    JsonObjectRequest addRequest = apiRequest.addBook(authorId, title, Integer.parseInt(date));
                    queue.add(addRequest);
                    Navigation.findNavController(root).navigate(R.id.navigation_home);
                }
                else {
                    Log.d("err", "onClick: il est mort avec postion "+position);
                }

            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("a changing", "onResume: Reloaded");
    }
}