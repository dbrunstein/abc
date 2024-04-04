package com.example.bookapp.ui.authors;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.APIRequest;
import com.example.bookapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class AuthorAdd extends Fragment {

    APIRequest apiRequest;
    RequestQueue addQueue;

    public AuthorAdd() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_author_add, container, false);
        Button add = root.findViewById(R.id.submit_add_author);
        addQueue = Volley.newRequestQueue(getContext());
        apiRequest = new APIRequest();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = ((EditText) root.findViewById(R.id.formFirstname)).getText().toString();
                String lastname = ((EditText) root.findViewById(R.id.formLastname)).getText().toString();

                // Effectuer la requête pour ajouter un nouvel auteur
                JsonObjectRequest addRequest = apiRequest.addAuthor(firstname, lastname);
                addQueue.add(addRequest);

                // Afficher un message de succès
                Snackbar.make(view, "Author Created", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Navigation.findNavController(root).navigate(R.id.navigation_dashboard);
            }
        });


        return root;
    }

}