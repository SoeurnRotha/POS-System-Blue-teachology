package com.example.bluesystemwithroomdatabase.DefultUser;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityDefaultUserBinding;

import java.util.Locale;

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
    public void setLocale(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getBaseContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("My_language", language);
        editor.apply();

    }

    public void loadLocale(){
        sharedPreferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_language", "");
        setLocale(language);
    }

    public void showDialogBox(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.design_alert_dialog, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
            }
        });

        alertDialog.show();
    }
}