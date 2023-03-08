package com.example.bluesystemwithroomdatabase.Customer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewCustomerBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForAddCustomerBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import MyAdapter.Customer_Adapter_RecyclerView;
import Mydatabase.BlueTeachnology_Database;

public class ViewCustomer extends AppCompatActivity {

    private static final int MY_REQUESTCODE = 10;
    ActivityViewCustomerBinding binding;
    BlueTeachnology_Dao blueTeachnology_dao;


    Customer_Adapter_RecyclerView adapter;
    List<Customer> customerList;
    boolean isEmpty = false;

    String gender ;
    File file;



    boolean IS_UPDATE = false;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchCustomer.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.searchCustomer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        binding.addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addCustomerVisible.setVisibility(view.VISIBLE);
                binding.viewCustomer.setVisibility(view.GONE);


            }
        });


        binding.backAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewCustomer.setVisibility(view.VISIBLE);
                binding.addCustomerVisible.setVisibility(view.GONE);
                recreate();
            }
        });
        binding.addImageCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
        Show_Data_From_Customer();

        Insert_Data_From_Custoemr();























    }

    private void Show_Data_From_Customer(){
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(this).blueTeachnology_dao();
        customerList = new ArrayList<>();
        customerList = blueTeachnology_dao.getAllCustomer();
        adapter = new Customer_Adapter_RecyclerView(customerList, getApplicationContext());
        binding.customerListview.setLayoutManager(new LinearLayoutManager(this));
        binding.customerListview.setAdapter(adapter);
    }


    private void Insert_Data_From_Custoemr(){
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        binding.saveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String customerName = binding.inputCustomerName.getText().toString();
                String customerPhone = binding.inputCustomerPhone.getText().toString();
                String customerAddress = binding.inputCustomerAddress.getText().toString();
                String customerEmail = binding.inputCustomerEmail.getText().toString();
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String date_create = simpleDateFormat.format(date);
                //is empty
                if(TextUtils.isEmpty(customerName) || TextUtils.isEmpty(customerPhone) || TextUtils.isEmpty(customerEmail) || TextUtils.isEmpty(customerAddress) ){

                    Toast.makeText(ViewCustomer.this, "Please input customer name and customer phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(binding.genderCustomerMele.isChecked()){
                    gender = "Mele";
                }

                if(binding.genderCustomerFemele.isChecked()){
                    gender = "Femele";
                }








//
//                if(isCustomerExist(customer)){
//                    Toast.makeText(AddCustomer.this, "Customer name Exist customer", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                blueTeachnology_dao.insertCustomer(customer);

                saveCustomer saveCustomer = new saveCustomer();
                saveCustomer.execute();

                Toast.makeText(ViewCustomer.this, "Add customer successful", Toast.LENGTH_SHORT).show();
                clearText();
                insertDone();
















            }
            public void insertDone(){
                CustomDialogDoneForAddCustomerBinding addCustomerBinding = CustomDialogDoneForAddCustomerBinding.inflate(getLayoutInflater());
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewCustomer.this);
                builder.setView(addCustomerBinding.getRoot());
                AlertDialog dialog = builder.create();
                dialog.show();

                addCustomerBinding.okDoneCustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }


            public void clearText(){
                binding.inputCustomerName.setText("");
                binding.inputCustomerAddress.setText("");
                binding.inputCustomerEmail.setText("");
                binding.inputCustomerPhone.setText("");
            }





        });

    }

    class saveCustomer extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);

            Customer customer = new Customer();

            customer.setCustomerName(binding.inputCustomerName.getText().toString().trim());
            customer.setCustomer_address(binding.inputCustomerAddress.getText().toString().trim());
            customer.setCustomer_email(binding.inputCustomerEmail.getText().toString().trim());
            customer.setCustomer_gender(gender);
            customer.setCustomer_phone(binding.inputCustomerPhone.getText().toString().trim());
            customer.setDate_time_create(date_create);
            customer.setCustomer_image(file.getPath());



            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertCustomer(customer);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
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

                    Uri uri=result.getData().getData();
                    file = new File(uri.getPath());

                    binding.addImageCustomer.setImageURI(uri);
                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });







}