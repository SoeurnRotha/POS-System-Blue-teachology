package com.example.bluesystemwithroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityLoginBinding;

import Dao.BlueTeachnology_Dao;
import Mydatabase.BlueTeachnology_Database;


public class LoginActivity extends AppCompatActivity {
    private Button logintoMain;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ActivityLoginBinding binding;


    BlueTeachnology_Dao blueTeachnology_dao;

    private static final String SHARED_PREF_NAME = "Blue_app";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(binding.getRoot());

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE );




        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        binding.buttonLoginInloginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.loginUsername.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if((username.equals("Admin")) && (password.equals("1234"))){

                    editor = preferences.edit();
                    editor.apply();


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);




                }
                else if(blueTeachnology_dao.login(username,password)){

                    editor = preferences.edit();
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}