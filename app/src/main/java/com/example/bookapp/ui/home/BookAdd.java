package com.example.bookapp.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookapp.R;
import com.example.bookapp.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.Console;
import java.util.ArrayList;
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bookAdd.
     */
    // TODO: Rename and change types and number of parameters
    public static BookAdd newInstance(String param1, String param2) {
        BookAdd fragment = new BookAdd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book_add, container, false);
        ArrayList<String> tagList = new ArrayList<>();

        // *** partie hand spinner ***
        Spinner spinner = (Spinner) root.findViewById(R.id.bookAddAuthor);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getTags().observe(getViewLifecycleOwner(), tags ->{
            for(Tag tag:tags){
                tagList.add(tag.getName());
            }
        });
        Log.d("VIVANT",tagList.toString());

        //ArrayAdapter<String> adapter = null;
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<String> adapter = new ArrayAdapter(
                root.getContext(), android.R.layout.simple_spinner_item,tagList);
/*
        viewModel.getTags().observe(getViewLifecycleOwner(), tags ->{
            for(Tag tag:tags){
                adapter.add(tag.getName());
            }

        });*/
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        // *** partie hand spinner ***

        Button addBook = root.findViewById(R.id.buttonBookAdd);

        addBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String title = ((EditText)root.findViewById(R.id.bookAddTitle)).getText().toString();


                Snackbar.make(root, title, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        return root;
    }
}