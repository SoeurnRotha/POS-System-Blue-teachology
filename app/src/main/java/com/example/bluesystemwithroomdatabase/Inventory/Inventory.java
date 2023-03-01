package com.example.bluesystemwithroomdatabase.Inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityInventoryBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.LocationTable;
import Model.PaymentMethod;
import MyAdapter.Location_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class Inventory extends AppCompatActivity {


    ActivityInventoryBinding binding;


    BlueTeachnology_Dao blueTeachnology_dao;

    List<LocationTable> locationTableList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showInventory.setVisibility(view.GONE);
                binding.showAddInventory.setVisibility(view.VISIBLE);
            }
        });
        binding.backToViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showInventory.setVisibility(view.VISIBLE);
                binding.showAddInventory.setVisibility(view.GONE);
            }
        });



        binding.addInventorySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLocation saveLocation = new saveLocation();
                saveLocation.execute();
            }
        });


        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        locationTableList = blueTeachnology_dao.getAllLocation();

        Location_Adapter adapter = new Location_Adapter(locationTableList);
        binding.listInventory.setLayoutManager(new LinearLayoutManager(this));
        binding.listInventory.setAdapter(adapter);








    }

    class saveLocation extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);

            LocationTable locationTable = new LocationTable();

            locationTable.setLocationName_eng(binding.addLocationEng.getText().toString().trim());
            locationTable.setLocationName_kh(binding.addLocationKh.getText().toString().trim());
            locationTable.setCreateDate(date_create);

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertLocaton(locationTable);



            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
        }
    }


}