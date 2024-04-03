package com.example.bookapp.ui.authors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapp.ViewModel;
import com.example.bookapp.R;
import com.example.bookapp.databinding.FragmentAuthorsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AuthorFragment extends Fragment{

    private FragmentAuthorsBinding binding;
    private AuthorAdapter mAdapter;
    private ViewModel dashboardViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         dashboardViewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding = FragmentAuthorsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Initialisation du RecyclerView
        RecyclerView mRecyclerView = binding.authorsRecycler;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Observer les données dans le ViewModel et mettre à jour l'adaptateur
        dashboardViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
            mAdapter = new AuthorAdapter(authors); // Mettre à jour les données de l'adaptateur
            mRecyclerView.setAdapter(mAdapter);
        });

        FloatingActionButton fab = root.findViewById(R.id.fabAuthor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.author_add);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        dashboardViewModel.load_authors();
    }
}