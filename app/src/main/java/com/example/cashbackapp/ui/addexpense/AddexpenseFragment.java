package com.example.cashbackapp.ui.addexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.databinding.FragmentAddexpenseBinding;

public class AddexpenseFragment extends Fragment {

    private FragmentAddexpenseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.example.cashbackapp.ui.addexpense.AddexpenseViewModel addexpenseViewModel =
                new ViewModelProvider(this).get(com.example.cashbackapp.ui.addexpense.AddexpenseViewModel.class);

        binding = FragmentAddexpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddexpense;
        addexpenseViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}