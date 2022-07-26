package com.example.cashbackapp.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashbackapp.MyDatabaseHelper;
import com.example.cashbackapp.R;
import com.example.cashbackapp.databinding.FragmentHomeBinding;

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
        if (myDB.readPetrolSpend() != null) {
            petrolSpendTextView.setText("RM " + myDB.readPetrolSpend());
        }
        if (myDB.readGroceriesSpend() != null) {
            groceriesSpendTextView.setText("RM " + myDB.readGroceriesSpend());
        }
        if (myDB.readEwalletSpend() != null) {
            ewalletSpendTextView.setText("RM " + myDB.readEwalletSpend());
        }
        if (myDB.readOthersSpend() != null) {
            othersSpendTextView.setText("RM " + myDB.readOthersSpend());
        }
        if (myDB.readTotalSpend() != null) {
            totalSpendTextView.setText("RM " + myDB.readTotalSpend());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}