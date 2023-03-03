package com.example.bluesystemwithroomdatabase.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityInventoryBinding;
import com.example.bluesystemwithroomdatabase.databinding.ActivityInvoiceBinding;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Invoice;
import MyAdapter.invoice_adapter.Invoice_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class InvoiceActivity extends AppCompatActivity {


    ActivityInvoiceBinding binding;
    List<Invoice> invoiceList;
    BlueTeachnology_Dao blueTeachnology_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        invoiceList = blueTeachnology_dao.getAllInvoice();

        Invoice_Adapter adapter = new Invoice_Adapter(invoiceList);
        binding.listInvoice.setLayoutManager(new LinearLayoutManager(this));
        binding.listInvoice.setAdapter(adapter);
    }
}