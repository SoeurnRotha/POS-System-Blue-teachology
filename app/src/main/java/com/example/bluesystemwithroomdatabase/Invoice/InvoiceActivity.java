package com.example.bluesystemwithroomdatabase.Invoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityInventoryBinding;
import com.example.bluesystemwithroomdatabase.databinding.ActivityInvoiceBinding;

public class InvoiceActivity extends AppCompatActivity {


    ActivityInvoiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}