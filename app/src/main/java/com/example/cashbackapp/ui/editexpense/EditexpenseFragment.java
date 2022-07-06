package com.example.cashbackapp.ui.editexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.databinding.FragmentEditexpenseBinding;

public class EditexpenseFragment extends Fragment {

    private FragmentEditexpenseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.example.cashbackapp.ui.editexpense.EditexpenseViewModel editexpenseViewModel =
                new ViewModelProvider(this).get(com.example.cashbackapp.ui.editexpense.EditexpenseViewModel.class);

        binding = FragmentEditexpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEditexpense;
        editexpenseViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}