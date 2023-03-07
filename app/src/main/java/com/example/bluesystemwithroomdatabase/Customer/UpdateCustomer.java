package com.example.bluesystemwithroomdatabase.Customer;

import static java.lang.Integer.parseInt;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateCustomerBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
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
    String image;
    boolean is_upload =false;

    int id;

    Uri uri;
    File file;


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


        binding.updateImageCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
                is_upload= true;
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
            Glide.with(this).load(customer.getCustomer_image()).into(binding.updateImageCustomer);
            image = customer.getCustomer_image();
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


            if(is_upload == true){

                customer.setCustomerId(id);
                customer.setCustomerName(binding.updateCustomerName.getText().toString().trim());
                customer.setCustomer_phone(binding.updateCustomerPhone.getText().toString().trim());
                customer.setCustomer_gender(binding.updateCustomerGender.getText().toString().trim());
                customer.setCustomer_email(binding.updateCustomerEmail.getText().toString().trim());
                customer.setCustomer_address(binding.updateCustomerAddress.getText().toString().trim());


                customer.setCustomer_image(image);
            }else{
                is_upload = false;

                customer.setCustomerId(id);
                customer.setCustomerName(binding.updateCustomerName.getText().toString().trim());
                customer.setCustomer_phone(binding.updateCustomerPhone.getText().toString().trim());
                customer.setCustomer_gender(binding.updateCustomerGender.getText().toString().trim());
                customer.setCustomer_email(binding.updateCustomerEmail.getText().toString().trim());
                customer.setCustomer_address(binding.updateCustomerAddress.getText().toString().trim());

                customer.setCustomer_image(image);

            }

            //set data to customer




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

    private void getImage(){
        launcher.launch(
                ImagePicker.Companion.with(this)
                        .maxResultSize(1080,1080, true)
                        .crop()
                        .galleryOnly()
                        .createIntent()
        );

    }
    ActivityResultLauncher<Intent> launcher=
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                if(result.getResultCode()==RESULT_OK){

                    assert result.getData() != null;

                    uri=result.getData().getData();
                    file = new File(uri.getPath());
                    image = file.toString();
                    binding.updateImageCustomer.setImageURI(uri);

                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });
}