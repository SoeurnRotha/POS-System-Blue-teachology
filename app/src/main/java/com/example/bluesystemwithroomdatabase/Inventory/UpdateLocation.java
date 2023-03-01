package com.example.bluesystemwithroomdatabase.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Customer.UpdateCustomer;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateLocationBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.LocationTable;
import Mydatabase.BlueTeachnology_Database;

public class UpdateLocation extends AppCompatActivity {


    ActivityUpdateLocationBinding binding;
    LocationTable locationTable;

    BlueTeachnology_Dao blueTeachnology_dao;

    int locationId;
    boolean IS_UPDATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInten();


        binding.updateLocationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
                recreate();
            }
        });


    }
    public void getInten(){
        if(getIntent().hasExtra("location")){
            IS_UPDATE = true;
            binding.updateLocationSave.setText("UPDATE");
            locationTable = (LocationTable) getIntent().getSerializableExtra("location");
            binding.updateLocationEnglish.setText(locationTable.getLocationName_eng());
            binding.updateLocationKhmer.setText(locationTable.getLocationName_kh());


            locationId = locationTable.getLocation_Id();
        }else {
            IS_UPDATE = false;
            binding.updateLocationSave.setText("SAVE");
        }

    }

    public void save(View view){
        if(IS_UPDATE == false){
            saveLocation saveLocation = new saveLocation();
            saveLocation.execute();
        }else{

            updateLocation updateLocation = new updateLocation();
            updateLocation.execute();


        }
    }

    class saveLocation extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);


            locationTable.setLocationName_eng(binding.updateLocationEnglish.getText().toString().trim());
            locationTable.setLocationName_kh(binding.updateLocationKhmer.getText().toString().trim());
            locationTable.setCreateDate(date_update);


            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

            blueTeachnology_dao.insertLocaton(locationTable);



            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            startActivity(new Intent(getApplicationContext(), ViewCustomer.class));

            Toast.makeText(UpdateLocation.this, "Save successful", Toast.LENGTH_SHORT).show();
        }
    }



    class updateLocation extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);


            //set data to customer
            locationTable.setLocation_Id(locationId);
            locationTable.setLocationName_eng(binding.updateLocationEnglish.getText().toString().trim());
            locationTable.setLocationName_kh(binding.updateLocationKhmer.getText().toString().trim());
            locationTable.setCreateDate(date_update);


            //connect db
            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            //query update
            blueTeachnology_dao.updateLocation(locationTable);





            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
        }
    }
}