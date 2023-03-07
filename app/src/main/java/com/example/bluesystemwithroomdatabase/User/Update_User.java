package com.example.bluesystemwithroomdatabase.User;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.Products.Update_products;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateUserBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.ProductTable;
import Model.UserTable;
import Mydatabase.BlueTeachnology_Database;

public class Update_User extends AppCompatActivity {
    ActivityUpdateUserBinding binding;
    private int uid;
    boolean IS_UPDATE = false;
    int userId;
    UserTable userTable;
    String imagePath;
    Uri uri;

    BlueTeachnology_Dao blueTeachnology_dao;

    File file;




    boolean isAdmin =false;
    boolean isManage = false;
    boolean isCashier = false;
    boolean isInsert = false;
    boolean isUpdate = false;
    boolean isDelete = false;
    boolean isView   =false;
    boolean isAllow  = false;
    boolean is_upload = false;


    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInten();

        binding.updateImageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
                is_upload = true;
            }
        });

        binding.updateAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.updateAllow.isChecked()){
                    binding.updateInsert.setEnabled(false);
                    binding.updateUpdate.setEnabled(false);
                    binding.updateDelete.setEnabled(false);
                    binding.updateView.setEnabled(false);


                    binding.updateInsert.setChecked(false);
                    binding.updateUpdate.setChecked(false);
                    binding.updateDelete.setChecked(false);
                    binding.updateView.setChecked(false);

                }else{
                    binding.updateInsert.setEnabled(true);
                    binding.updateUpdate.setEnabled(true);
                    binding.updateDelete.setEnabled(true);
                    binding.updateView.setEnabled(true);
                }
            }
        });

        binding.updateSaveNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });




    }


    public void getInten(){
        if(getIntent().hasExtra("user")){
            IS_UPDATE = true;
            binding.updateSaveNow.setText("UPDATE");


            userTable = (UserTable) getIntent().getSerializableExtra("user");
            binding.updateFristName.setText(userTable.getFrist_name());
            binding.updateLastName.setText(userTable.getLast_name());
            binding.updateUserName.setText(userTable.getUsername());
            binding.updateGenderUser.setText(userTable.getGender());


            if(userTable.isAdmin() == false) {
                isAdmin = false;
            }else {
                isAdmin = true;
            }
            if(userTable.isManager() == false){
                isManage = false;
            }else{
                isManage = true;
            }

            if(userTable.isCashier() == false){
                isCashier = false;
            }else{
                isCashier = true;
            }


            if(userTable.isInsert() == false){
                isInsert = false;
            }else {
                isInsert = true;
            }

            if(userTable.isUpdate() == false){
                isUpdate = false;

            }else {
                isUpdate = true;

            }

            if (userTable.isDelete() == false){
                isDelete = false;

            }else {
                isDelete = true;

            }

            if(userTable.isView() == false){
                isView = false;

            }else{
                isView = true;
            }

            if (userTable.isAllow() == false){
                isAllow = false;
            }else{
                isAllow = true;
            }



            //cheack
            binding.updateAdmin.setChecked(isAdmin);
            binding.updateManager.setChecked(isManage);
            binding.updateCashier.setChecked(isCashier);
            binding.updateInsert.setChecked(isInsert);
            binding.updateUpdate.setChecked(isUpdate);
            binding.updateDelete.setChecked(isDelete);
            binding.updateView.setChecked(isView);
            binding.updateAllow.setChecked(isAllow);



            if(binding.updateAdmin.isChecked()){
                isAdmin = true;
            }
            if(binding.updateManager.isChecked()){
                isManage = true;
            }
            if(binding.updateCashier.isChecked()){
                isCashier = true;
            }
            if(binding.updateInsert.isChecked()){
                isInsert= true;
            }
            if(binding.updateUpdate.isChecked()){
                isUpdate = true;
            }
            if(binding.updateDelete.isChecked()){
                isDelete = true;
            }
            if(binding.updateView.isChecked()){
                isView = true;
            }
            if(binding.updateAllow.isChecked()){
                isAllow =true;

            }


            //show image
            Glide.with(this).load(userTable.getUserImage()).into(binding.updateImageUser);
            userId = userTable.getUserId();
            imagePath = userTable.getUserImage();


        }else {
            IS_UPDATE = false;
            binding.updateSaveNow.setText("SAVE");
        }

    }


    public void save(View view){
        if(IS_UPDATE == false){
            SaveUser saveUser = new SaveUser();
            saveUser.execute();
        }else{
            UpdateUser updateUser = new UpdateUser();
            updateUser.execute();
        }
    }


    class SaveUser extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {


            userTable.setUserId(userId);

            userTable.setFrist_name(binding.updateFristName.getText().toString());
            userTable.setLast_name(binding.updateLastName.getText().toString());
            userTable.setUsername(binding.updateUserName.getText().toString());
            userTable.setGender(binding.updateGenderUser.getText().toString());

            userTable.setAdmin(isAdmin);
            userTable.setManager(isManage);
            userTable.setCashier(isCashier);

            userTable.setInsert(isInsert);
            userTable.setUpdate(isUpdate);
            userTable.setDelete(isDelete);
            userTable.setView(isView);
            userTable.setAllow(isAllow);

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertUser(userTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            finish();
        }
    }


    class UpdateUser extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault() );
            String update_date = simpleDateFormat.format(date);

            ProductTable productTable = new ProductTable();
            if(is_upload == true){
                userTable.setUserId(userId);

                userTable.setFrist_name(binding.updateFristName.getText().toString());
                userTable.setLast_name(binding.updateLastName.getText().toString());
                userTable.setUsername(binding.updateUserName.getText().toString());
                userTable.setGender(binding.updateGenderUser.getText().toString());

                userTable.setAdmin(isAdmin);
                userTable.setManager(isManage);
                userTable.setCashier(isCashier);

                userTable.setInsert(isInsert);
                userTable.setUpdate(isUpdate);
                userTable.setDelete(isDelete);
                userTable.setView(isView);
                userTable.setAllow(isAllow);


                productTable.setImage_product(imagePath);
            }else{
                is_upload = false;

                userTable.setUserId(userId);

                userTable.setFrist_name(binding.updateFristName.getText().toString());
                userTable.setLast_name(binding.updateLastName.getText().toString());
                userTable.setUsername(binding.updateUserName.getText().toString());
                userTable.setGender(binding.updateGenderUser.getText().toString());

                userTable.setAdmin(binding.updateAdmin.isChecked());
                userTable.setManager(binding.updateManager.isChecked());
                userTable.setCashier(binding.updateCashier.isChecked());

                userTable.setInsert(binding.updateInsert.isChecked());
                userTable.setUpdate(binding.updateUpdate.isChecked());
                userTable.setDelete(binding.updateDelete.isChecked());
                userTable.setView(binding.updateView.isChecked());
                userTable.setAllow(binding.updateAllow.isChecked());


                productTable.setImage_product(imagePath);

            }


            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.updateUser(userTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            finish();
            Toast.makeText(Update_User.this, "Update successful", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearText(){

        binding.updateFristName.setText("");
        binding.updateLastName.setText("");
        binding.updateUserName.setText("");
        binding.updateGenderUser.setText("");


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

                    uri=result.getData().getData();
                    file = new File(uri.getPath());
                    imagePath = file.toString();
                    binding.updateImageUser.setImageURI(uri);

                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });
}