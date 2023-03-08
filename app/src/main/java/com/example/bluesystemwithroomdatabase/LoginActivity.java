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
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityLoginBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;

import Dao.BlueTeachnology_Dao;
import Mydatabase.BlueTeachnology_Database;


public class LoginActivity extends AppCompatActivity {
    private Button logintoMain;

    SharedPreferences preferences;
    SharedPreferences sharedPreferences;
    ActivityLoginBinding binding;


    BlueTeachnology_Dao blueTeachnology_dao;
    File file;


    private static final String SHARED_NAME = "blue";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PROFILE = "profile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(SHARED_NAME, MODE_PRIVATE );





        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        binding.buttonLoginInloginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.loginUsername.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if((username.equals("Admin")) && (password.equals("1234"))){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME,username);
                    editor.putString(KEY_PASSWORD, password);



                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);




                }
                else if(blueTeachnology_dao.login(username,password)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME,username);
                    editor.putString(KEY_PASSWORD, password);
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));


                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    ActivityResultLauncher<Intent> launcher=
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                if(result.getResultCode()==RESULT_OK){

                    assert result.getData() != null;

                    Uri uri=result.getData().getData();
                    file = new File(uri.getPath());

                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });
}