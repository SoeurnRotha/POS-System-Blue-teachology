package com.example.bluesystemwithroomdatabase.Pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityPosBinding;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import MyAdapter.pos_adapter.Pos_category_adapter_show_list_view;
import Mydatabase.BlueTeachnology_Database;

public class Pos extends AppCompatActivity {


    ActivityPosBinding binding;

    BlueTeachnology_Dao blueTeachnology_dao;
    List<CategoryTable> categoryTableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        categoryTableList = blueTeachnology_dao.getAllCateroy();

        Pos_category_adapter_show_list_view adapter_show_list_category = new Pos_category_adapter_show_list_view(categoryTableList);
        binding.listCategoryPos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.listCategoryPos.setAdapter(adapter_show_list_category);
    }
}