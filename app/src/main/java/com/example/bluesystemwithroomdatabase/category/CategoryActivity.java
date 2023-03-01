package com.example.bluesystemwithroomdatabase.category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityCategoryBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForAddCategoryBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Mydatabase.BlueTeachnology_Database;

public class CategoryActivity extends AppCompatActivity {


    ActivityCategoryBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.categorySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String c_name_eng = binding.inputCategoryNameEnglish.getText().toString();
                String c_name_kh = binding.inputCategoryNameKhmer.getText().toString();

                Date date = Calendar.getInstance().getTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

                String category_date = simpleDateFormat.format(date);


//                CategoryTable categoryTable = new CategoryTable( c_name_eng, c_name_kh, category_date);


                 BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

//                 blueTeachnology_dao.insertCaterory(categoryTable);


                Toast.makeText(CategoryActivity.this, "You Insert category successful....", Toast.LENGTH_SHORT).show();


//                binding.inputCategoryId.setText("");
                binding.inputCategoryNameEnglish.setText("");
                binding.inputCategoryNameKhmer.setText("");


                CustomDialogDoneForAddCategoryBinding addCategoryBinding = CustomDialogDoneForAddCategoryBinding.inflate(getLayoutInflater());
                AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
                builder.setView(addCategoryBinding.getRoot());

                AlertDialog dialog = builder.create();
                dialog.show();

                addCategoryBinding.okDoneCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });
    }
}