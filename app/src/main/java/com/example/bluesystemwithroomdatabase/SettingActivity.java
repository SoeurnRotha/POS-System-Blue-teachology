package com.example.bluesystemwithroomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {


    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void button_click(View view){
        binding.radioGroup.getCheckedRadioButtonId();

        Toast.makeText(this, "Select ", Toast.LENGTH_SHORT).show();
    }

}

