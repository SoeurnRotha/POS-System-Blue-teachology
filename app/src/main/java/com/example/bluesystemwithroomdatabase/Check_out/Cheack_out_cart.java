package com.example.bluesystemwithroomdatabase.Check_out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCheackOutCartBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Cheackout;
import MyAdapter.CheckOut_Adapter.CheckOut_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class Cheack_out_cart extends AppCompatActivity {

    ActivityCheackOutCartBinding binding;
    BlueTeachnology_Dao blueTeachnology_dao;
    List<Cheackout> cheackoutList;
    CheckOut_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheackOutCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        blueTeachnology_dao = BlueTeachnology_Database.getInstance(this).blueTeachnology_dao();
        cheackoutList = blueTeachnology_dao.getAllCheackout();
        adapter = new CheckOut_Adapter(cheackoutList);
        binding.listCheackOut.setLayoutManager(new LinearLayoutManager(this));
        binding.listCheackOut.setAdapter(adapter);



    }
}