package com.example.cashbackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateExpense extends AppCompatActivity {
    private Context context;
    //form fields
    EditText amount_input, date_input;
    Spinner category_input;
    Button update_button, delete_button;

    String id, category, date;
    String amount;

    DatePickerDialog picker;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);
        category_input = findViewById(R.id.spinner11);
        amount_input = findViewById(R.id.editTextNumberDecimal1);
        date_input = findViewById(R.id.editTextDate1);
        date_input.setInputType(InputType.TYPE_NULL);
        update_button = findViewById(R.id.update);
        delete_button = findViewById(R.id.delete);

        //First we call this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateExpense.this);
                category = category_input.getSelectedItem().toString();
                amount = amount_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                myDB.updateExpense(id, category, amount, date);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        //select date field
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateExpense.this,
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
                                date_input.setText(year+"-"+substr1+"-"+substr2);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("category") &&
                getIntent().hasExtra("amount") && getIntent().hasExtra("date")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            category = getIntent().getStringExtra("category");
            amount = getIntent().getStringExtra("amount");
            date = getIntent().getStringExtra("date");

            //dropdown expense category field
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.type, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            category_input.setAdapter(adapter);
            //Setting Intent Data
            category_input.getSelectedItem();
            amount_input.setText(amount);
            date_input.setText(date);
            Log.d("stev", category + " " + amount + " " + date);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this expense ?");
        builder.setMessage("Are you sure you want to delete " + category + " expense?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateExpense.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}