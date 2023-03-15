package com.example.bluesystemwithroomdatabase.DefultUser;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityDefaultUserBinding;

public class DefaultUser extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ActivityDefaultUserBinding binding;
    private static final String KEY_USERNAME = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showDefault(binding.navigationViewDefault.getHeaderView(0));


        binding.defaultToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayoutDefault.openDrawer(GravityCompat.START);
            }
        });
        setSupportActionBar(binding.defaultToolbar);
        binding.navigationViewDefault.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , binding.drawerLayoutDefault,binding.defaultToolbar, R.string.open_nav, R.string.close_nav);
        toggle.syncState();

    }

    public void showDefault(View view){
        sharedPreferences = getSharedPreferences("default", MODE_PRIVATE);
        TextView username =view.findViewById(R.id.show_default_username);
        username.setText(sharedPreferences.getString(KEY_USERNAME, null));
    }
}