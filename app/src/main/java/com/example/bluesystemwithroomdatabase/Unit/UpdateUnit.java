package com.example.bluesystemwithroomdatabase.Unit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.Customer.UpdateCustomer;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateUnitBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.UnitModel;
import Mydatabase.BlueTeachnology_Database;

public class UpdateUnit extends AppCompatActivity {

    ActivityUpdateUnitBinding binding;

    boolean IS_UPDATE = false;
    UnitModel unitModel;
    int unitId;
    BlueTeachnology_Dao blueTeachnology_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateUnitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInten();


        binding.updateUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });
    }

    public void getInten(){
        if(getIntent().hasExtra("unit")){
            IS_UPDATE = true;
            binding.updateUnit.setText("UPDATE");
            unitModel = (UnitModel) getIntent().getSerializableExtra("unit");

            binding.updateUnitTitle.setText(unitModel.getUnitName());
            binding.updateUnitType.setText(unitModel.getUnitCode());
            unitId = unitModel.getUnitId();
        }else {
            IS_UPDATE = false;
            binding.updateUnit.setText("SAVE");
        }

    }



    public void save(View view){
        if(IS_UPDATE == false){
            saveUnit saveUnit = new saveUnit();
            saveUnit.execute();
        }else{
            updateUnit updateUnit = new updateUnit();
            updateUnit.execute();
        }
    }

    class saveUnit extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);
            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            unitModel.setUnitName(binding.updateUnitTitle.getText().toString().trim());
            unitModel.setUnitCode(binding.updateUnitType.getText().toString().trim());
            unitModel.setCreateAt(date_update);
            blueTeachnology_dao.insertUnit(unitModel);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            startActivity(new Intent(getApplicationContext(), ViewCustomer.class));
            Toast.makeText(UpdateUnit.this, "Save successful", Toast.LENGTH_SHORT).show();
        }
    }



    class updateUnit extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            unitModel.setUnitId(unitId);
            unitModel.setUnitName(binding.updateUnitTitle.getText().toString().trim());
            unitModel.setUnitCode(binding.updateUnitType.getText().toString().trim());

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.updateUnit(unitModel);


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            Toast.makeText(UpdateUnit.this, "Update successful", Toast.LENGTH_SHORT).show();
        }
    }

}