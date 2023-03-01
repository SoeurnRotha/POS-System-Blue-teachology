package com.example.bluesystemwithroomdatabase.Customer;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateCustomerBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;

import Model.Customer;
import Mydatabase.BlueTeachnology_Database;

public class UpdateCustomer extends AppCompatActivity {

    ActivityUpdateCustomerBinding binding;
    private Customer customer;

    boolean IS_UPDATE = false;

    int id;


    BlueTeachnology_Dao blueTeachnology_dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getInten();

        binding.updateCustomerSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });






    }

    public void getInten(){
        if(getIntent().hasExtra("customer")){
            IS_UPDATE = true;
            binding.updateCustomerSave.setText("UPDATE");
            customer = (Customer) getIntent().getSerializableExtra("customer");
            binding.updateCustomerName.setText(customer.getCustomerName());
            binding.updateCustomerPhone.setText(customer.getCustomer_phone());
            binding.updateCustomerEmail.setText(customer.getCustomer_email());
            binding.updateCustomerAddress.setText(customer.getCustomer_address());
            binding.updateCustomerGender.setText(customer.getCustomer_gender());

            id = customer.getCustomerId();
        }else {
            IS_UPDATE = false;
            binding.updateCustomerSave.setText("SAVE");
        }

    }



    public void clearText(){
        binding.updateCustomerGender.setText("");
        binding.updateCustomerAddress.setText("");
        binding.updateCustomerEmail.setText("");
        binding.updateCustomerPhone.setText("");
        binding.updateCustomerName.setText("");
        finish();
    }

    public void save(View view){
        if(IS_UPDATE == false){
            saveCustomer saveCustomer = new saveCustomer();
            saveCustomer.execute();
        }else{


            updateCustomer updateCustomer = new updateCustomer();
            updateCustomer.execute();
        }
    }

    class saveCustomer extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);



            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

//            customer.setCustomerId(id);
            customer.setCustomerName(binding.updateCustomerName.getText().toString().trim());
            customer.setCustomer_phone(binding.updateCustomerPhone.getText().toString().trim());
            customer.setCustomer_gender(binding.updateCustomerGender.getText().toString().trim());
            customer.setCustomer_email(binding.updateCustomerEmail.getText().toString().trim());
            customer.setCustomer_address(binding.updateCustomerAddress.getText().toString().trim());
            customer.setDate_time_create(date_update);

            blueTeachnology_dao.insertCustomer(customer);



            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            startActivity(new Intent(getApplicationContext(), ViewCustomer.class));

            Toast.makeText(UpdateCustomer.this, "Save successful", Toast.LENGTH_SHORT).show();
        }
    }



    class updateCustomer extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            //set data to customer
            customer.setCustomerId(id);
            customer.setCustomerName(binding.updateCustomerName.getText().toString().trim());
            customer.setCustomer_phone(binding.updateCustomerPhone.getText().toString().trim());
            customer.setCustomer_gender(binding.updateCustomerGender.getText().toString().trim());
            customer.setCustomer_email(binding.updateCustomerEmail.getText().toString().trim());
            customer.setCustomer_address(binding.updateCustomerAddress.getText().toString().trim());



            //connect db
            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            //query update
            blueTeachnology_dao.updateCustomer(customer);


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            Toast.makeText(UpdateCustomer.this, "Update successful", Toast.LENGTH_SHORT).show();
        }
    }
}