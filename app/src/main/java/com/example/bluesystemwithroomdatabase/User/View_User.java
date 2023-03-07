package com.example.bluesystemwithroomdatabase.User;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewUserBinding;
import com.github.drjacky.imagepicker.ImagePicker;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.UserTable;
import MyAdapter.User_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class View_User extends AppCompatActivity {


    ActivityViewUserBinding binding;
    boolean mele = false;
    boolean femele = false;
    File file;

    boolean admin = false;
    boolean manager = false;
    boolean cashier = false;

    boolean insert = false;
    boolean update =false;
    boolean delete = false;
    boolean views =false;
    boolean allow =false;

    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//date
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);


        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(this).blueTeachnology_dao();
        List<UserTable> userTableList = blueTeachnology_dao.getAlluserInfo();

        User_Adapter user_adapter = new User_Adapter(userTableList, getApplicationContext());
        binding.listUser.setLayoutManager(new LinearLayoutManager(this));
        binding.listUser.setAdapter(user_adapter);

        recreate();



        binding.buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showUser.setVisibility(view.GONE);
                binding.addUserinFo.setVisibility(view.VISIBLE);
            }
        });
        binding.backUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showUser.setVisibility(view.VISIBLE);
                binding.addUserinFo.setVisibility(view.GONE);
            }
        });

        binding.buttonSeleteBodUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(View_User.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        month = month +1 ;
                        String date = dayOfmonth+"/"+month+"/"+year;
                        binding.inputBodUser.setText(date);

                    }
                },year,month,day);
                dialog.show();

            }
        });
        binding.checkboxAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.checkboxAllow.isChecked()){
                    binding.checkboxView.setEnabled(false);
                    binding.checkboxDelete.setEnabled(false);
                    binding.checkboxInsert.setEnabled(false);
                    binding.checkboxUpdate.setEnabled(false);
                }else{
                    binding.checkboxView.setEnabled(true);
                    binding.checkboxDelete.setEnabled(true);
                    binding.checkboxInsert.setEnabled(true);
                    binding.checkboxUpdate.setEnabled(true);
                }
//
            }
        });

        binding.imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });


        binding.cardUserSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.genderMele.isChecked()){
                    mele = true;
                    gender = "Mele";
                    Toast.makeText(View_User.this, "Mele = " + mele, Toast.LENGTH_SHORT).show();
                }
                if(binding.genderFemele.isChecked()){
                    femele = true;
                    gender = "Femele";
                    Toast.makeText(View_User.this, "Femele = " + femele, Toast.LENGTH_SHORT).show();
                }




                binding.groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        Toast.makeText(View_User.this, "Select" + i, Toast.LENGTH_SHORT).show();
                    }
                });

                if(binding.checkboxAdmin.isChecked() ){
                    admin = true;
                    Toast.makeText(View_User.this, "Admin = " + admin, Toast.LENGTH_SHORT).show();
                } if(binding.checkboxManager.isChecked()){

                    manager = true;
                    Toast.makeText(View_User.this, "Manager = " + manager, Toast.LENGTH_SHORT).show();
                } if (binding.checkboxCashier.isChecked()) {

                    cashier = true;
                    Toast.makeText(View_User.this, "Cashier = " + cashier, Toast.LENGTH_SHORT).show();
                }



                //cheack box
                if(binding.checkboxInsert.isChecked()){
                    Toast.makeText(View_User.this, "Insert", Toast.LENGTH_SHORT).show();
                }

                if(binding.checkboxUpdate.isChecked()){
                    Toast.makeText(View_User.this, "Update", Toast.LENGTH_SHORT).show();
                }

                if(binding.checkboxDelete.isChecked()){
                    Toast.makeText(View_User.this, "Delete", Toast.LENGTH_SHORT).show();
                }

                if(binding.checkboxView.isChecked()){
                    Toast.makeText(View_User.this, "View", Toast.LENGTH_SHORT).show();
                }

                if(binding.checkboxAllow.isChecked()){
                    Toast.makeText(View_User.this, "Allow", Toast.LENGTH_SHORT).show();


                }


                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String create_date = simpleDateFormat.format(date);







                String first_name= binding.inputFristName.getText().toString();
                String last_namme = binding.inputLastName.getText().toString();
                String user_name = binding.editUserInputUsername.getText().toString();
                String password = binding.editUserInputPassword.getText().toString();
                mele = binding.genderMele.isChecked();
                femele = binding.genderFemele.isChecked();

                admin = binding.checkboxAdmin.isChecked();
                manager = binding.checkboxManager.isChecked()  ;
                cashier = binding.checkboxCashier.isChecked()  ;

                insert = binding.checkboxInsert.isChecked();
                update = binding.checkboxUpdate.isChecked();
                delete = binding.checkboxDelete.isChecked();
                views = binding.checkboxView.isChecked();
                allow = binding.checkboxAllow.isChecked();



                UserTable userTable = new UserTable();
                userTable.setUserImage(file.getPath());
                userTable.setFrist_name(first_name);
                userTable.setLast_name(last_namme);
                userTable.setDate_create_user(create_date);
                userTable.setUsername(user_name);
                userTable.setPassword(password);
                userTable.setGender(gender);
                userTable.setBrith_of_date(binding.inputBodUser.getText().toString());
                userTable.setAdmin(admin);
                userTable.setManager(manager);
                userTable.setCashier(cashier);
                userTable.setInsert(insert);
                userTable.setUpdate(update);
                userTable.setDelete(delete);
                userTable.setView(views);
                userTable.setAllow(allow);



                blueTeachnology_dao.insertUser(userTable);
            }
        });



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

                    binding.imageUser.setImageURI(uri);
                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });
}