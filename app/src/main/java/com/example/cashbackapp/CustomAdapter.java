package com.example.cashbackapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList expense_id, expense_category, expense_amount, expense_date;

    public CustomAdapter(Activity activity, Context context, ArrayList expense_id, ArrayList expense_category, ArrayList expense_amount,
                         ArrayList expense_date) {
        this.activity = activity;
        this.context = context;
        this.expense_id = expense_id;
        this.expense_category = expense_category;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.expense_id_txt.setText(String.valueOf(expense_id.get(position)));
        holder.expense_id_txt.setText("");
        holder.expense_category_txt.setText(String.valueOf(expense_category.get(position)));
        holder.expense_amount_txt.setText(String.valueOf(expense_amount.get(position)));
        holder.expense_date_txt.setText(String.valueOf(expense_date.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateExpense.class);
                intent.putExtra("id", String.valueOf(expense_id.get(position)));
                intent.putExtra("category", String.valueOf(expense_category.get(position)));
                intent.putExtra("amount", String.valueOf(expense_amount.get(position)));
                intent.putExtra("date", String.valueOf(expense_date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expense_id_txt, expense_category_txt, expense_amount_txt, expense_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id_txt = itemView.findViewById(R.id.expense_id_txt);
            expense_category_txt = itemView.findViewById(R.id.expense_category_txt);
            expense_amount_txt = itemView.findViewById(R.id.expense_amount_txt);
            expense_date_txt = itemView.findViewById(R.id.expense_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
