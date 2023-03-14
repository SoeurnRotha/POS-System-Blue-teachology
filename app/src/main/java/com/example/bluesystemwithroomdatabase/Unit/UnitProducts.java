package com.example.bluesystemwithroomdatabase.Unit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUnitProductsBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForAddCustomerBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForAddUnitBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.UnitModel;
import MyAdapter.UnitAdapter.UnitAdapter;
import Mydatabase.BlueTeachnology_Database;
import kotlin.Unit;

public class UnitProducts extends AppCompatActivity {
    ActivityUnitProductsBinding binding;
    BlueTeachnology_Dao blueTeachnology_dao;
    List<UnitModel> unitModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUnitProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddUnit.setVisibility(View.VISIBLE);
                binding.showUnit.setVisibility(View.GONE);

            }
        });

        binding.backUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddUnit.setVisibility(View.GONE);
                binding.showUnit.setVisibility(View.VISIBLE);
            }
        });

        recreate();
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        unitModelList = blueTeachnology_dao.getAllUnit();

        UnitAdapter adapter = new UnitAdapter(unitModelList, getApplicationContext());
        binding.listUnit.setLayoutManager(new LinearLayoutManager(this));
        binding.listUnit.setAdapter(adapter);

        binding.saveUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUnit saveUnit = new saveUnit();
                saveUnit.execute();

            }
        });

    }


    class saveUnit extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);

            UnitModel unitModel = new UnitModel();
            unitModel.setUnitName(binding.unitTitle.getText().toString().trim());
            unitModel.setUnitCode(binding.unitType.getText().toString().trim());
            unitModel.setCreateAt(date_create);

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertUnit(unitModel);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            insertDone();
        }
    }

    public void clearText(){
        binding.unitTitle.setText("");
        binding.unitType.setText("");
    }


    public void insertDone(){
        CustomDialogDoneForAddUnitBinding addUnitBinding = CustomDialogDoneForAddUnitBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitProducts.this);
        builder.setView(addUnitBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();

        addUnitBinding.okDoneCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


}