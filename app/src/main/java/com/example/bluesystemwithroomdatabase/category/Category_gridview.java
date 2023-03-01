package com.example.bluesystemwithroomdatabase.category;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluesystemwithroomdatabase.Products.ViewProducts;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCategoryGridviewBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Model.ProductTable;
import MyAdapter.Category_baseAdapter;
import Mydatabase.BlueTeachnology_Database;

public class Category_gridview extends AppCompatActivity {

    ActivityCategoryGridviewBinding binding;

    BlueTeachnology_Dao blueTeachnology_dao;

    int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCategoryGridviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addCateroty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addCaterotyVisible.setVisibility(view.VISIBLE);
                binding.viewCategory.setVisibility(view.GONE);


            }
        });


        binding.backViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addCaterotyVisible.setVisibility(view.GONE);
                binding.viewCategory.setVisibility(view.VISIBLE);
            }
        });


        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(this).blueTeachnology_dao();
        List<CategoryTable> categoryTableList = blueTeachnology_dao.getAllCateroy();

        Category_baseAdapter category_adapter = new Category_baseAdapter(categoryTableList, getApplicationContext());

        binding.categoryGridview.setAdapter(category_adapter);

        binding.categorySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveCategory saveCategory = new SaveCategory();
                saveCategory.execute();
            }
        });



        binding.categoryGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Category_gridview.this, "Selete = " + i, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Category_gridview.this, Update_Category2.class);

                intent.putExtra("c_id", String.valueOf(categoryTableList.get(i).getCategoryID()));
                intent.putExtra("cname_eng", String.valueOf(categoryTableList.get(i).getCategoryname_Eng()));
                intent.putExtra("cname_kh", String.valueOf(categoryTableList.get(i).getCategoryname_kh()));

                startActivity(intent);

                finish();



            }
        });




        //node
//        binding.categoryGridview.setOnItemClickListener((adapterView, view, i, l) -> {
//            CustomDiglogUpdateCategoryBinding alertbinding;
//            alertbinding = CustomDiglogUpdateCategoryBinding.inflate(getLayoutInflater());
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setView(alertbinding.getRoot());
//            AlertDialog alertDialog = builder.create();
//
//
//            alertbinding.categoryNo.setOnClickListener(v -> alertDialog.dismiss());
//            alertDialog.show();
//
//
//
//
//
//
//
//            alertbinding.categoryUpdateNameEnglish.setText(categoryTableList.get(i).getNameEng());
//            alertbinding.categoryUpdateNameKhmer.setText(categoryTableList.get(i).getNameKh());
//
//            Toast.makeText(this, "Selete : " + i, Toast.LENGTH_SHORT).show();
//
//            alertbinding.categoryYes.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    blueTeachnology_dao.updateCategoryByid(
//                            alertbinding.categoryUpdateId.getText().toString(),
//                            alertbinding.categoryUpdateNameEnglish.getText().toString(),
//                            alertbinding.categoryUpdateNameKhmer.getText().toString()
//                    );
//                    recreate();
//                }
//
//            });
//
//
//
//        });




    }

    class SaveCategory extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);


            String pname_eng,pname_kh;


            pname_eng = binding.inputCategoryNameEnglish.getText().toString();
            pname_kh = binding.inputCategoryNameKhmer.getText().toString();
            CategoryTable categoryTable = new CategoryTable();

            categoryTable.setCategory_date(date_create);
            categoryTable.setCategoryname_Eng(pname_eng);
            categoryTable.setCategoryname_kh(pname_kh);


            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

            blueTeachnology_dao.insertCaterory(categoryTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            Toast.makeText(Category_gridview.this, "Save record", Toast.LENGTH_SHORT).show();
        }
    }
}