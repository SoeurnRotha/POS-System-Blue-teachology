package com.example.bluesystemwithroomdatabase.category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateCategory2Binding;

import Dao.BlueTeachnology_Dao;
import Mydatabase.BlueTeachnology_Database;

public class Update_Category2 extends AppCompatActivity {

    ActivityUpdateCategory2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateCategory2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.updateCategoryId.setText(getIntent().getStringExtra("c_id"));
        binding.updateCategoryNameEng.setText(getIntent().getStringExtra("cname_eng"));
        binding.updateCategoryNameKh.setText(getIntent().getStringExtra("cname_kh"));
        binding.updateCategorySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cid = Integer.parseInt(binding.updateCategoryId.getText().toString());

                BlueTeachnology_Dao blueTeachnology_dao =   BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
                Toast.makeText(Update_Category2.this, "You update category successful...........", Toast.LENGTH_SHORT).show();
                blueTeachnology_dao.updateCategoryByid(cid , binding.updateCategoryNameEng.getText().toString(), binding.updateCategoryNameKh.getText().toString());
                finish();

            }
        });
    }
}