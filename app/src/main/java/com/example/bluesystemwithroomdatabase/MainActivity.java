package com.example.bluesystemwithroomdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.bluesystemwithroomdatabase.Cart.Cart;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.Inventory.Inventory;
import com.example.bluesystemwithroomdatabase.PaymentMethod.ViewPaymentMethod;
import com.example.bluesystemwithroomdatabase.Products.ViewProducts;

import com.example.bluesystemwithroomdatabase.User.View_User;
import com.example.bluesystemwithroomdatabase.category.Category_gridview;
import com.example.bluesystemwithroomdatabase.databinding.ActivityMainBinding;
import com.example.bluesystemwithroomdatabase.testImage.TestImagePage;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "Blue_app";
    Toolbar toolbar;

//    Adapter_class adapter_class;
//
//    List<String> title;
//    List<Integer> image;
//    GridView gridView;


    ActivityMainBinding binding;

    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();



        binding.toolbar.setTitle("Blue Teachnology");
//
//        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
//        List<CategoryWithProduct> categoryWithProducts = blueTeachnology_dao.getCategoryWithProducts();
//        //test
//        Log.d("CategoryWithProduct", "" + categoryWithProducts.toString());


        setSupportActionBar(toolbar);


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
                Intent intent = new Intent(MainActivity.this, TestImagePage.class);
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
                Toast.makeText(this, "Language", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                logout();
                break;
        }
        return true;
    }
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        editor = preferences.edit();
        editor.clear();
        editor.commit();
        builder.setTitle("LOGOUT");
        finish();




        builder.setMessage("Do you want to logout");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                //toast
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
}