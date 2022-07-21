package com.example.cashbackapp.ui.editexpense;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.R;
import com.example.cashbackapp.databinding.FragmentEditexpenseBinding;

public class EditexpenseFragment extends Fragment {

    //form fields
    EditText amount_input, date_input, category_input;
//    Spinner category_input;
    Button update_button, delete_button;

    String id, category, amount, date;

    private FragmentEditexpenseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditexpenseViewModel editexpenseViewModel =
                new ViewModelProvider(this).get(EditexpenseViewModel.class);

        binding = FragmentEditexpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        category_input = root.findViewById(R.id.spinner11);
        amount_input = root.findViewById(R.id.editTextNumberDecimal1);
        date_input = root.findViewById(R.id.editTextDate1);
        update_button = root.findViewById(R.id.update);
        delete_button = root.findViewById(R.id.delete);

        //First we call this
        getAndSetIntentData();

        return root;
    }

    void getAndSetIntentData(){
        if(getActivity().getIntent().hasExtra("id") && getActivity().getIntent().hasExtra("category") &&
                getActivity().getIntent().hasExtra("amount") && getActivity().getIntent().hasExtra("date")){
            //Getting Data from Intent
            id = getActivity().getIntent().getStringExtra("id");
            category = getActivity().getIntent().getStringExtra("category");
            amount = getActivity().getIntent().getStringExtra("amount");
            date = getActivity().getIntent().getStringExtra("date");

            //Setting Intent Data
            category_input.setText(category);
            amount_input.setText(amount);
            date_input.setText(date);
            Log.d("stev", category+" "+amount+" "+date);
        }else{
            Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}