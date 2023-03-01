package com.example.bluesystemwithroomdatabase.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateUserBinding;

import Dao.BlueTeachnology_Dao;
import Mydatabase.BlueTeachnology_Database;

public class Update_User extends AppCompatActivity {
    ActivityUpdateUserBinding binding;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.updateId.setText(getIntent().getStringExtra("uid"));
        uid = Integer.parseInt(binding.updateId.getText().toString());
        binding.updateUsername.setText(getIntent().getStringExtra("uname"));
        binding.updatePassword.setText(getIntent().getStringExtra("upassword"));


        binding.updateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

                blueTeachnology_dao.updateUserBYid(uid,binding.updateUsername.getText().toString(), binding.updatePassword.getText().toString());
                Toast.makeText(Update_User.this, "You update category successful...........", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                recreate();
            }
        });


    }
}