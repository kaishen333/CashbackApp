package com.example.cashbackapp.ui.addexpense;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.MainActivity;
import com.example.cashbackapp.R;
import com.example.cashbackapp.databinding.FragmentAddexpenseBinding;

import java.util.Calendar;

public class AddexpenseFragment extends Fragment {

    private FragmentAddexpenseBinding binding;

    DatePickerDialog picker;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AddexpenseViewModel addexpenseViewModel =
                new ViewModelProvider(this).get(AddexpenseViewModel.class);

        binding = FragmentAddexpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        final TextView textView = binding.textAddexpense;
//        addexpenseViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        EditText eText = (EditText) getView().findViewById(R.id.editTextDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}