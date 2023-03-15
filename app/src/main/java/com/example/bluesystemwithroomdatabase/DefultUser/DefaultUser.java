package com.example.bluesystemwithroomdatabase.DefultUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Default_fragment.Default_add_user;
import com.example.bluesystemwithroomdatabase.Default_fragment.Default_pos;
import com.example.bluesystemwithroomdatabase.MainActivity;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityDefaultUserBinding;
import com.google.android.material.navigation.NavigationView;

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
        loadLocale();

        binding.defaultToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayoutDefault.openDrawer(GravityCompat.START);
            }
        });
        setSupportActionBar(binding.defaultToolbar);
        binding.navigationViewDefault.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , binding.drawerLayoutDefault, binding.defaultToolbar, R.string.open_nav, R.string.close_nav);
        toggle.syncState();


        binding.navigationViewDefault.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                binding.drawerLayoutDefault.closeDrawer(GravityCompat.START);
                item.setChecked(true);

                switch (item.getItemId()){
                    case R.id.nav_logout_default:
                        showDialogBox();
                        break;

                    case R.id.nav_user:
                        Toast.makeText(DefaultUser.this, "User", Toast.LENGTH_SHORT).show();
                        replaceFragment(new Default_add_user());
                        break;

                    case R.id.nav_home:
                        Toast.makeText(DefaultUser.this, "Home", Toast.LENGTH_SHORT).show();
                        replaceFragment(new Default_pos());
                        break;
                    case R.id.nav_language_default:
                        selectLanguage();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });



    }

    public void selectLanguage(){
        final String[] listItem = {"English", "Khmer"};
        AlertDialog.Builder builder = new AlertDialog.Builder(DefaultUser.this);
        builder.setTitle("Choose Language....");
        builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
                if(i==1){
                    setLocale("km");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_default_user, fragment);
        fragmentTransaction.commit();
    }
}