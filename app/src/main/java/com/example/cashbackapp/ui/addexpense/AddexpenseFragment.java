package com.example.cashbackapp.ui.addexpense;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.MyDatabaseHelper;
import com.example.cashbackapp.R;
import com.example.cashbackapp.databinding.FragmentAddexpenseBinding;

import java.util.Calendar;

public class AddexpenseFragment extends Fragment {

    //form fields
    EditText date, amount;
    Spinner spinner1;
    Button submit;

    DatePickerDialog picker;
    EditText eText;
    private FragmentAddexpenseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AddexpenseViewModel addexpenseViewModel =
                new ViewModelProvider(this).get(AddexpenseViewModel.class);

        binding = FragmentAddexpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //dropdown expense category field
        Spinner dropdown = root.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        //select date field
        eText = root.findViewById(R.id.editTextDate);
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
                                Integer mon1 = (monthOfYear + 1);
                                String mon2 = String.valueOf(mon1);
                                String mon3 = '0'+mon2;
                                String substr1 = mon3.substring(mon3.length() - 2);
                                Integer day1 = dayOfMonth;
                                String day2 = String.valueOf(day1);
                                String day3 = '0'+day2;
                                String substr2 = day3.substring(day3.length() - 2);
                                eText.setText(year+"-"+substr1+"-"+substr2);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        //form submission
        spinner1 = root.findViewById(R.id.spinner1);
        amount = root.findViewById(R.id.editTextNumberDecimal);
        date = root.findViewById(R.id.editTextDate);
        submit = root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                myDB.addExpense(spinner1.getSelectedItem().toString(), amount.getText().toString().trim(), date.getText().toString().trim());

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