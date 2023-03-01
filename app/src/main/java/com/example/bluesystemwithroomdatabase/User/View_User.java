package com.example.bluesystemwithroomdatabase.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewUserBinding;


import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.UserTable;
import MyAdapter.User_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class View_User extends AppCompatActivity {


    ActivityViewUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(this).blueTeachnology_dao();
        List<UserTable> userTableList = blueTeachnology_dao.getAlluserInfo();

        User_Adapter user_adapter = new User_Adapter(userTableList);
        binding.listUser.setLayoutManager(new LinearLayoutManager(this));
        binding.listUser.setAdapter(user_adapter);

        recreate();



        binding.buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_User.this, Add_User.class);
                startActivity(intent);
            }
        });
    }
}