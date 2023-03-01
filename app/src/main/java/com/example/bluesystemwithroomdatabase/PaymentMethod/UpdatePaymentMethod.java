package com.example.bluesystemwithroomdatabase.PaymentMethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Customer.UpdateCustomer;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdatePaymentMethodBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.PaymentMethod;
import Mydatabase.BlueTeachnology_Database;

public class UpdatePaymentMethod extends AppCompatActivity {

    BlueTeachnology_Dao blueTeachnology_dao;
    boolean ISUPDATE =  false;
    int payment_id;

    PaymentMethod paymentMethod;

    ActivityUpdatePaymentMethodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getInten();


        binding.updatePaymentSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });

    }


    public void getInten(){
        if(getIntent().hasExtra("payment_method")){
            ISUPDATE = true;
            binding.updatePaymentSave.setText("UPDATE");


            paymentMethod = (PaymentMethod) getIntent().getSerializableExtra("payment_method");
            binding.updatePaymentDes.setText(paymentMethod.getDecription());

            payment_id = paymentMethod.getPaymentId();
        }else {
            ISUPDATE = false;
            binding.updatePaymentSave.setText("SAVE");
        }

    }




    public void save(View view){
        if(ISUPDATE == false){
            savePayment savePayment = new savePayment();
            savePayment.execute();
        }else{
            updatePayment updatePayment = new updatePayment();
            updatePayment.execute();
        }
    }

    class savePayment extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);



            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

//            customer.setCustomerId(id);
            paymentMethod.setDecription(binding.updatePaymentDes.getText().toString().trim());
            paymentMethod.setPayment_date(date_update);




            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            startActivity(new Intent(getApplicationContext(), ViewCustomer.class));

            Toast.makeText(UpdatePaymentMethod.this, "Save successful", Toast.LENGTH_SHORT).show();
        }
    }



    class updatePayment extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
            String date_update = simpleDateFormat.format(date);

            paymentMethod.setPaymentId(payment_id);
            paymentMethod.setDecription(binding.updatePaymentDes.getText().toString().trim());
            paymentMethod.setPayment_date(date_update);

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.updatePayment(paymentMethod);

            binding.updatePaymentDes.setText("");


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            Toast.makeText(UpdatePaymentMethod.this, "Update payment method successful", Toast.LENGTH_SHORT).show();
        }
    }

}
