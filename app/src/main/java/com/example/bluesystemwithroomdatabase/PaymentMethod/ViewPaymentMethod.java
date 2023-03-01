package com.example.bluesystemwithroomdatabase.PaymentMethod;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewPaymentMethodBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForPaymentMethodBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.PaymentMethod;
import MyAdapter.Payment_Method_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class ViewPaymentMethod extends AppCompatActivity {
    ActivityViewPaymentMethodBinding binding;

    BlueTeachnology_Dao blueTeachnology_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewPaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();


        List<PaymentMethod> paymentMethodList = blueTeachnology_dao.getAllPayments();
        Payment_Method_Adapter adapter = new Payment_Method_Adapter(paymentMethodList);

        binding.listviewPayment.setLayoutManager(new LinearLayoutManager(this));
        binding.listviewPayment.setAdapter(adapter);

    



        binding.addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewPaymentMethod.setVisibility(view.GONE);
                binding.addPaymentMethod.setVisibility(view.VISIBLE);
            }
        });


        binding.backViewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewPaymentMethod.setVisibility(view.VISIBLE);
                binding.addPaymentMethod.setVisibility(view.GONE);
            }
        });


        binding.savePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePaymentMethod savePaymentMethod = new savePaymentMethod();
                savePaymentMethod.execute();
            }
        });


    }


    public void done(){
        CustomDialogDoneForPaymentMethodBinding alertBinding = CustomDialogDoneForPaymentMethodBinding.inflate(getLayoutInflater());

        AlertDialog.Builder builder = new AlertDialog.Builder(ViewPaymentMethod.this);
        builder.setView(alertBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();


        alertBinding.okDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    class savePaymentMethod extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);

            PaymentMethod paymentMethod = new PaymentMethod();

            paymentMethod.setDecription(binding.inputPaymentDes.getText().toString());
            paymentMethod.setPayment_date(date_create);

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertPayment(paymentMethod);


            binding.inputPaymentDes.setText("");


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            done();
            finish();


        }
    }


}