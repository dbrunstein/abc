package com.example.bookapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // repositery pour les requetes
    // mutable live data propre au livres

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        homeViewModel.getBooks().observe(getViewLifecycleOwner(), books ->{

            RecyclerView bookRecycler = getView().findViewById(R.id.bookRecycler);
            if (bookRecycler == null) {
                Log.d("error", "book recycler null");
            }
            bookRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            bookRecycler.setAdapter(new BookAdapter(books,getContext()));
        });



        /*
                pokeViewModel = new ViewModelProvider(this).get(PokeViewModel.class);
        pokeViewModel.getJson().observe(this,pokemons -> {

            RecyclerView pokeRCV = findViewById(R.id.pokeRecycler);
            if (pokeRCV == null) {
                Log.d("mouloude", "AK IS DEAD");
            }
            //JSONArray jsonArray = pokeViewModel.getJson().getValue();
            pokeRCV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            pokeRCV.setAdapter(new PokeAdapter(pokemons,this));
        });


        */

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}