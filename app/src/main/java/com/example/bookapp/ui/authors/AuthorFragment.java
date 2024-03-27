package com.example.bookapp.ui.authors;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.databinding.FragmentAuthorsBinding;

public class AuthorFragment extends Fragment{

    private FragmentAuthorsBinding binding;
    private AuthorAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AuthorsViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AuthorsViewModel.class);

        binding = FragmentAuthorsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialisation du RecyclerView
        RecyclerView mRecyclerView = binding.authorsRecycler;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialisation de l'adaptateur pour le RecyclerView


        // Observer les données dans le ViewModel et mettre à jour l'adaptateur
        dashboardViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
                    mAdapter = new AuthorAdapter(authors); // Adapter que vous avez créé
                    mRecyclerView.setAdapter(mAdapter);
        });
            return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}