package com.example.bookapp.ui.books;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ViewModel;
import com.example.bookapp.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewModel viewModel;
    // repositery pour les requetes
    // mutable live data propre au livres

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView bookRecycler = binding.bookRecycler;
        bookRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getBooks().observe(getViewLifecycleOwner(), books ->{

            if (bookRecycler == null) {
                Log.d("error", "book recycler null");
            }
            BookAdapter adapter= new BookAdapter(books);
            bookRecycler.setAdapter(adapter);
        });

        FloatingActionButton fab = root.findViewById(R.id.fabBook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("TAG", "onClick: IL EST VIVANT");
                //Snackbar.make(view, "Pimp my book", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                Navigation.findNavController(view).navigate(R.id.book_add);

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
        Log.d("onResume", "onResume: changed");
        viewModel.load_books();
    }
}
