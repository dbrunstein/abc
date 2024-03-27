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
import com.example.bookapp.ui.authors.AuthorAdapter;

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
        RecyclerView bookRecycler = binding.bookRecycler;
        bookRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("test", String.valueOf(homeViewModel.getBooks().getValue().size()));

        homeViewModel.getBooks().observe(getViewLifecycleOwner(), books ->{

            //RecyclerView bookRecycler = getView().findViewById(R.id.bookRecycler);
            Log.d("books", homeViewModel.getBooks().getValue().get(0).getTitle());
            if (bookRecycler == null) {
                Log.d("error", "book recycler null");
            }
            BookAdapter adapter= new BookAdapter(books);
            bookRecycler.setAdapter(adapter);
            Log.d("size", String.valueOf(adapter.getItemCount()));
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}