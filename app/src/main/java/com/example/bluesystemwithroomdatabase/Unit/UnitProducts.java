package com.example.bluesystemwithroomdatabase.Unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUnitProductsBinding;

public class UnitProducts extends AppCompatActivity {
    ActivityUnitProductsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUnitProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddUnit.setVisibility(View.VISIBLE);
                binding.showUnit.setVisibility(View.GONE);
            }
        });

        binding.backUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddUnit.setVisibility(View.GONE);
                binding.showUnit.setVisibility(View.VISIBLE);
            }
        });

    }
}