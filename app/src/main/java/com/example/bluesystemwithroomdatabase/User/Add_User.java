package com.example.bluesystemwithroomdatabase.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityAddUserBinding;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.UserTable;
import Mydatabase.BlueTeachnology_Database;

public class Add_User extends AppCompatActivity {

    private static boolean isAllow = false;


    private RecyclerView recyclerView;



    ActivityAddUserBinding binding;
    boolean mele = false;
    boolean femele = false;

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

        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        binding.buttonSeleteBodUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(Add_User.this, new DatePickerDialog.OnDateSetListener() {
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


        BlueTeachnology_Dao blueTeachnology_dao =    BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

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

        binding.cardUserSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.genderMele.isChecked()){
                    mele = true;
                    gender = "Mele";
                    Toast.makeText(Add_User.this, "Mele = " + mele, Toast.LENGTH_SHORT).show();
                }
                if(binding.genderFemele.isChecked()){
                    femele = true;
                    gender = "Femele";
                    Toast.makeText(Add_User.this, "Femele = " + femele, Toast.LENGTH_SHORT).show();
                }




                binding.groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        Toast.makeText(Add_User.this, "Select" + i, Toast.LENGTH_SHORT).show();
                    }
                });

                 if(binding.checkboxAdmin.isChecked() ){
                     admin = true;
                    Toast.makeText(Add_User.this, "Admin = " + admin, Toast.LENGTH_SHORT).show();
                } if(binding.checkboxManager.isChecked()){

                     manager = true;
                    Toast.makeText(Add_User.this, "Manager = " + manager, Toast.LENGTH_SHORT).show();
                } if (binding.checkboxCashier.isChecked()) {

                     cashier = true;
                    Toast.makeText(Add_User.this, "Cashier = " + cashier, Toast.LENGTH_SHORT).show();
                }



                 //cheack box
                 if(binding.checkboxInsert.isChecked()){
                     Toast.makeText(Add_User.this, "Insert", Toast.LENGTH_SHORT).show();
                 }

                 if(binding.checkboxUpdate.isChecked()){
                     Toast.makeText(Add_User.this, "Update", Toast.LENGTH_SHORT).show();
                 }

                 if(binding.checkboxDelete.isChecked()){
                     Toast.makeText(Add_User.this, "Delete", Toast.LENGTH_SHORT).show();
                 }

                 if(binding.checkboxView.isChecked()){
                     Toast.makeText(Add_User.this, "View", Toast.LENGTH_SHORT).show();
                 }

                 if(binding.checkboxAllow.isChecked()){
                     Toast.makeText(Add_User.this, "Allow", Toast.LENGTH_SHORT).show();


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



                 blueTeachnology_dao.insertUser(new UserTable(

                         first_name,
                         last_namme,
                         user_name,
                         password,
                         gender,
                         binding.inputBodUser.getText().toString(),
                         create_date,
                         admin,
                         manager,
                         cashier,
                         insert,
                         update,
                         delete,
                         views,
                         allow

                 ));
            }
        });










//
//        binding.editUserInputUsername.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String userName = editable.toString();
//
//                if(blueTeachnology_dao.is_token(userName)){
//
//                    isAllow = false;
//                    Toast.makeText(Add_User.this, "Already Token", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    isAllow = true;
//                }
//
//            }
//        });
//
//
//        binding.cardUserSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                if(isAllow){
//                    UserTable userTable = new UserTable(0, binding.editUserInputUsername.getText().toString(), binding.editUserInputPassword.getText().toString());
//                    blueTeachnology_dao.insertUser(userTable);
//
//                    Toast.makeText(Add_User.this, "You create user successful", Toast.LENGTH_SHORT).show();
//
//                    binding.editUserInputUsername.setText("");
//                    binding.editUserInputPassword.setText("");
//
//
//                    recreate();
//                }else{
//                    Toast.makeText(Add_User.this, "Username already token", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }


}