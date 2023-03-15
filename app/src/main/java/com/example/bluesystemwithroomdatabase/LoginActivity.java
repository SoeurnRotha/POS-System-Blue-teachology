package com.example.bluesystemwithroomdatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.DefultUser.DefaultUser;
import com.example.bluesystemwithroomdatabase.databinding.ActivityLoginBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.UserTable;
import Mydatabase.BlueTeachnology_Database;


public class LoginActivity extends AppCompatActivity {
    private Button logintoMain;

    SharedPreferences preferences;
    SharedPreferences sharedPreferences;
    ActivityLoginBinding binding;


    BlueTeachnology_Dao blueTeachnology_dao;
    File file;


    private static final String SHARED_NAME = "blue";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USERROLES = "userRoles";
    private static final String KEY_PROFILE = "profile";

    String USER_ROLES;
    String USER_NAME;
    String PATH_IMAGE;

    List<UserTable> userTableList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        binding.buttonLoginInloginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.loginUsername.getText().toString();
                String password = binding.loginPassword.getText().toString();
                userTableList =blueTeachnology_dao.loginAccount(username ,password);
                sharedPreferences = getSharedPreferences(SHARED_NAME,MODE_PRIVATE);
                if((username.equals("Admin")) && (password.equals("1234"))){
                    //default user
//                    sharedPreferences = getSharedPreferences("default",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_USERNAME,username);
                    editor.putString(KEY_USERROLES, "ADMIN");
                    editor.putBoolean("hasLogin", true);

                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, DefaultUser.class);
                    startActivity(intent);
                    finish();
                }
                for(int i=0;i< userTableList.size(); i++){
                    if(username.equals(userTableList.get(i).getUsername()) && password.equals(userTableList.get(i).getPassword())){
                        if(userTableList.get(i).isAdmin()){
                            USER_ROLES = "Admin";
                        }
                        if(userTableList.get(i).isManager()){
                            USER_ROLES = "Manager";
                        }
                        if(userTableList.get(i).isCashier()){
                            USER_ROLES = "Cashier";
                        }

                        USER_NAME = userTableList.get(i).getUsername();
                        PATH_IMAGE = userTableList.get(i).getUserImage();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USERROLES, USER_ROLES);
                        editor.putString(KEY_USERNAME, USER_NAME);
                        editor.putString(KEY_PROFILE, PATH_IMAGE);
                        editor.putBoolean("hasLogin", true);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalidate Login", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

}