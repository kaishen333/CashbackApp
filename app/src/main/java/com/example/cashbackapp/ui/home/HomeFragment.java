package com.example.cashbackapp.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.MyDatabaseHelper;
import com.example.cashbackapp.R;
import com.example.cashbackapp.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;

public class HomeFragment extends Fragment {
    MyDatabaseHelper myDB;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final DecimalFormat df = new DecimalFormat("0.00");
        TextView cashbackTextView = root.findViewById(R.id.text_amount);
        TextView monthTextView = root.findViewById(R.id.text_cashback);
        TextView petrolSpendTextView = root.findViewById(R.id.petrolSpend);
        TextView petrolCashbackTextView = root.findViewById(R.id.petrolCashback);
        TextView groceriesSpendTextView = root.findViewById(R.id.groceriesSpend);
        TextView groceriesCashbackTextView = root.findViewById(R.id.groceriesCashback);
        TextView ewalletSpendTextView = root.findViewById(R.id.ewalletSpend);
        TextView ewalletsCashbackTextView = root.findViewById(R.id.ewalletsCashback);
        TextView othersSpendTextView = root.findViewById(R.id.othersSpend);
        TextView othersCashbackTextView = root.findViewById(R.id.othersCashback);
        TextView totalSpendTextView = root.findViewById(R.id.text_totalSpend);
        TextView diffSpendkTextView = root.findViewById(R.id.text_hint2);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());
        monthTextView.setText("Cashback for " + month_name);

        myDB = new MyDatabaseHelper(root.getContext());
        if (myDB.readTotalSpend() != null) {
            totalSpendTextView.setText("RM " + myDB.readTotalSpend());
            Double val = Double.parseDouble(myDB.readTotalSpend());
            Integer cap = 2000;
            ProgressBar progressBar = root.findViewById(R.id.progressBar);
            progressBar.setMax(2000);
            if (val < cap) {
                Double petrolCb = 0.0;
                Double petrolEcb = 0.01;
                Double groceriesCb = 0.002;
                Double groceriesEcb = 0.008;
                Double ewalletCb = 0.002;
                Double ewalletEcb = 0.008;
                Double othersCb = 0.002;
                Double othersEcb = 0.0;
                Double diff = cap - val;
                Double totalcb = 0.0;
                diffSpendkTextView.setText("RM " + diff);
                if (myDB.readPetrolSpend() != null) {
                    petrolSpendTextView.setText("RM " + myDB.readPetrolSpend());
                    Double petrolCash = Double.parseDouble(myDB.readPetrolSpend())*petrolEcb;
                    if (petrolCash > 15){
                        petrolCash = 15.0;
                    }
                    totalcb+=petrolCash;
                    petrolCashbackTextView.setText("RM "+df.format(petrolCash).toString());
                }
                if (myDB.readGroceriesSpend() != null) {
                    groceriesSpendTextView.setText("RM " + myDB.readGroceriesSpend());
                    Double groceriesCash = Double.parseDouble(myDB.readGroceriesSpend())*groceriesCb;
                    Double grocerieseCash = Double.parseDouble(myDB.readGroceriesSpend())*groceriesEcb;
                    if (grocerieseCash > 15.0){
                        grocerieseCash = 15.0;
                    }
                    Double groceriesTotal = groceriesCash + grocerieseCash;
                    totalcb+=groceriesTotal;
                    groceriesCashbackTextView.setText("RM "+df.format(groceriesTotal).toString());
                }
                if (myDB.readEwalletSpend() != null) {
                    ewalletSpendTextView.setText("RM " + myDB.readEwalletSpend());
                    Double ewalletCash = Double.parseDouble(myDB.readEwalletSpend())*ewalletCb;
                    Double ewalleteCash = Double.parseDouble(myDB.readEwalletSpend())*ewalletEcb;
                    if (ewalleteCash > 15){
                        ewalleteCash = 15.0;
                    }
                    Double ewalletTotal = ewalletCash + ewalleteCash;
                    totalcb+=ewalletTotal;
                    ewalletsCashbackTextView.setText("RM "+df.format(ewalletTotal).toString());
                }
                if (myDB.readOthersSpend() != null) {
                    othersSpendTextView.setText("RM " + myDB.readOthersSpend());
                    Double othersCash = Double.parseDouble(myDB.readOthersSpend())*othersCb;
                    if (othersCash > 15){
                        othersCash = 15.0;
                    }
                    totalcb+=othersCash;
                    othersCashbackTextView.setText("RM "+df.format(othersCash).toString());
                }
                cashbackTextView.setText("RM "+df.format(totalcb).toString());
                int value = (int) Math.round(val);
                progressBar.setProgress(value);
            } else {
                Double petrolCb = 0.0;
                Double petrolEcb = 0.08;
                Double groceriesCb = 0.002;
                Double groceriesEcb = 0.078;
                Double ewalletCb = 0.002;
                Double ewalletEcb = 0.078;
                Double othersCb = 0.002;
                Integer othersEcb = 0;
                Double totalcb = 0.0;
                if (myDB.readPetrolSpend() != null) {
                    petrolSpendTextView.setText("RM " + myDB.readPetrolSpend());
                    Double petrolCash = Double.parseDouble(myDB.readPetrolSpend())*petrolEcb;
                    if (petrolCash > 15){
                        petrolCash = 15.0;
                    }
                    totalcb+=petrolCash;
                    petrolCashbackTextView.setText("RM "+df.format(petrolCash).toString());
                }
                if (myDB.readGroceriesSpend() != null) {
                    groceriesSpendTextView.setText("RM " + myDB.readGroceriesSpend());
                    Double groceriesCash = Double.parseDouble(myDB.readGroceriesSpend())*groceriesCb;
                    Double grocerieseCash = Double.parseDouble(myDB.readEwalletSpend())*groceriesEcb;
                    if (grocerieseCash > 15){
                        grocerieseCash = 15.0;
                    }
                    Double groceriesTotal = groceriesCash + grocerieseCash;
                    totalcb+=groceriesTotal;
                    groceriesCashbackTextView.setText("RM "+df.format(groceriesTotal).toString());
                }
                if (myDB.readEwalletSpend() != null) {
                    ewalletSpendTextView.setText("RM " + myDB.readEwalletSpend());
                    Double ewalletCash = Double.parseDouble(myDB.readEwalletSpend())*ewalletCb;
                    Double ewalleteCash = Double.parseDouble(myDB.readEwalletSpend())*ewalletEcb;
                    if (ewalleteCash > 15){
                        ewalleteCash = 15.0;
                    }
                    Double ewalletTotal = ewalletCash + ewalleteCash;
                    totalcb+=ewalletTotal;
                    ewalletsCashbackTextView.setText("RM "+df.format(ewalletTotal).toString());
                }
                if (myDB.readOthersSpend() != null) {
                    othersSpendTextView.setText("RM " + myDB.readOthersSpend());
                    Double othersCash = Double.parseDouble(myDB.readOthersSpend())*othersCb;
                    if (othersCash > 15){
                        othersCash = 15.0;
                    }
                    totalcb+=othersCash;
                    othersCashbackTextView.setText("RM "+df.format(othersCash).toString());
                }
                cashbackTextView.setText("RM "+df.format(totalcb).toString());
                progressBar.setProgress(2000);
            }

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}