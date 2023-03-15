package com.example.bluesystemwithroomdatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.Cart.Cart;
import com.example.bluesystemwithroomdatabase.CreatePDF_for_invoice.CreatePDF;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.Expanse.ViewAllExpanse;
import com.example.bluesystemwithroomdatabase.Inventory.Inventory;
import com.example.bluesystemwithroomdatabase.Invoice.InvoiceActivity;
import com.example.bluesystemwithroomdatabase.PaymentMethod.ViewPaymentMethod;
import com.example.bluesystemwithroomdatabase.Pos.Pos;
import com.example.bluesystemwithroomdatabase.Products.ViewProducts;

import com.example.bluesystemwithroomdatabase.Unit.UnitProducts;
import com.example.bluesystemwithroomdatabase.User.View_User;
import com.example.bluesystemwithroomdatabase.category.Category_gridview;
import com.example.bluesystemwithroomdatabase.databinding.ActivityMainBinding;

import com.example.bluesystemwithroomdatabase.databinding.CustomDiglogCheangLanguageBinding;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    ActivityMainBinding binding;

    File file;
    Uri uri;

    private static final String SHARED_NAME = "blue";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USERROLES = "userRoles";
    private static final String KEY_PROFILE = "profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //language
        loadLocale();
        //set app bar
        binding.toolbar.setTitle("Blue Teachnology");
        binding.toolbar.setTitleTextColor(Color.WHITE);
        View headerView = binding.navigationView.getHeaderView(0);
        updateHeader(headerView);
        setSupportActionBar(toolbar);
        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        binding.navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , binding.drawerLayout,toolbar, R.string.open_nav, R.string.close_nav);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);

        binding.cardInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Invoice", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });
        
        binding.cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, View_User.class);
                startActivity(intent);
            }
        });
        
        binding.cardCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Setupitem", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(MainActivity.this, ViewCustomer.class);
                startActivity(intent);
            }
        });
        
        binding.cardReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Reports", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CreatePDF.class);
                startActivity(intent);

            }
        });
        
        binding.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this , Category_gridview.class);
                startActivity(intent);

            }
        });
        
        
        binding.cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "About us", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this , AboutActivity.class);
                startActivity(intent);
            }
        });
        binding.cartProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Products", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this , ViewProducts.class);
                startActivity(intent);
            }
        });



        binding.cardCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);
            }
        });


        binding.cardLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Inventory.class);
                startActivity(intent);
            }
        });






        binding.cardPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewPaymentMethod.class);
                startActivity(intent);
            }
        });


        binding.cardExpanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewAllExpanse.class);
                startActivity(intent);
            }
        });


        binding.cardPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pos.class);
                startActivity(intent);
            }
        });



    }
    

    @Override
    public void onBackPressed() {

        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_language:
//
//                CustomDiglogCheangLanguageBinding alertbinding;
//                alertbinding = CustomDiglogCheangLanguageBinding.inflate(getLayoutInflater());
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setView(alertbinding.getRoot());
//
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();

                final String[] listItem = {"English", "Khmer"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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


                break;
            case R.id.nav_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
//                logout();
                showDialogBox();
                break;

            case R.id.nav_unit:
                Intent intent = new Intent(MainActivity.this, UnitProducts.class);
                startActivity(intent);
                break;
        }
        return true;
    }
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("");
        builder.setMessage("Do you want to logout");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }


    public void updateHeader(View headerView){

        sharedPreferences = getSharedPreferences(SHARED_NAME,MODE_PRIVATE);
        ImageView profileUser = headerView.findViewById(R.id.show_userimage);
        TextView username = headerView.findViewById(R.id.show_username);
        TextView userRoles = headerView.findViewById(R.id.show_userRoles);
        username.setText(sharedPreferences.getString(KEY_USERNAME, null));
        userRoles.setText(sharedPreferences.getString(KEY_USERROLES, null));
        String path = sharedPreferences.getString(KEY_PROFILE, null);
        Glide.with(this).load(path).into(profileUser);
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

