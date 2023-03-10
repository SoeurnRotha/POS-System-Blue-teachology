package com.example.bluesystemwithroomdatabase.Check_out;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCheackOutCartBinding;

import java.util.ArrayList;

public class Cheack_out_cart extends AppCompatActivity {

    ActivityCheackOutCartBinding binding;
    ArrayList<String> qty;
    ArrayList<String> price;
    ArrayList<String> amount;
    ArrayList<String> description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheackOutCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();



        String total = getIntent().getStringExtra("total");

        qty = bundle.getStringArrayList("qty");
        price = bundle.getStringArrayList("price");
        amount =bundle.getStringArrayList("amount");
        description =bundle.getStringArrayList("name");
//
//        ArrayList<String> storeQty = new ArrayList<>();
//        storeQty.add(String.valueOf(qty));
        binding.cheackoutTotal.setText(total);


        Log.d("TAGQTY", ""+qty.size());
        Log.d("TAGQTY", ""+price);
        Log.d("TAGQTY", ""+amount);
        Log.d("TAGQTY", ""+description);
        String qtyStr;
        String priceStr;
        String amountStr;
        String desStr;
        for(int i =0 ; i< qty.size() ; i++){
            qtyStr = qty.get(i);
             priceStr= price.get(i);
            amountStr = amount.get(i);
            desStr = description.get(i);

            binding.cheackoutNo.setText(""+(i+1));

            binding.cheackoutQty.setText(qtyStr);
            binding.cheackoutDes.setText(desStr);
            binding.cheackoutPrice.setText(priceStr);
            binding.cheackoutAmount.setText(amountStr);
            Log.d("TAGQTY", "qty ="+i);
            Log.d("TAGQTY", "price ="+priceStr);
            Log.d("TAGQTY", "amount ="+amountStr);
            Log.d("TAGQTY", "des ="+desStr);


        }


//
//        binding.cheackoutDes.setText(String.valueOf(description));
//        binding.cheackoutQty.setText(String.valueOf(qty));
//        binding.cheackoutPrice.setText(String.valueOf(price));
//        binding.cheackoutAmount.setText(String.valueOf(amount));
//
//
//        binding.rowCheackout.addView(binding.cheackoutNo);
//        binding.rowCheackout.addView(binding.cheackoutDes);
//        binding.rowCheackout.addView(binding.cheackoutQty);
//        binding.rowCheackout.addView(binding.cheackoutPrice);
//        binding.rowCheackout.addView(binding.cheackoutAmount);
//
//        binding.tableCheackout.addView(binding.rowCheackout);


    }
}