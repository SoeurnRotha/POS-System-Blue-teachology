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


    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInten();





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
                Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
            }
            if(userTable.isManager() == false){
                isManage = false;
            }else{
                isManage = true;
                Toast.makeText(this, "Manager", Toast.LENGTH_SHORT).show();
            }

            if(userTable.isCashier() == false){
                isCashier = false;
            }else{
                isCashier = true;
                Toast.makeText(this, "Cashier", Toast.LENGTH_SHORT).show();
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








            //show image
            Glide.with(this).load(userTable.getUserImage()).into(binding.updateImageUser);
            userId = userTable.getUserId();
            imagePath = userTable.getUserImage();


        }else {
            IS_UPDATE = false;
            binding.updateSaveNow.setText("SAVE");
        }

    }


//    public void save(View view){
//        if(IS_UPDATE == false){
//            Update_products.SaveProducts saveCustomer = new Update_products.SaveProducts();
//            saveCustomer.execute();
//        }else{
//            Update_products.UpdateProducts updateProducts = new Update_products.UpdateProducts();
//            updateProducts.execute();
//        }
//    }


    class SaveUser extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            userTable.setUserId(userId);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            finish();
        }
    }


    class UpdateProducts extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault() );
            String update_date = simpleDateFormat.format(date);

            ProductTable productTable = new ProductTable();
//            if(is_upload == true){
//                productTable.setProductId(productId);
//                productTable.setCategoryID(cid);
//                productTable.setLocationId(location_id);
//
//                productTable.setProductName_eng(binding.updateProductNameEnglish.getText().toString());
//                productTable.setProductName_kh(binding.updateProductNameKhmer.getText().toString());
//                productTable.setProduct_qty(Integer.parseInt(binding.updateProductQty.getText().toString()));
//                productTable.setProduct_barCode(binding.updateProductBarcode.getText().toString());
//                productTable.setProduct_Price(Double.parseDouble(binding.updateProductPrice.getText().toString()));
//                productTable.setProduct_cost(Double.parseDouble(binding.updateProductCost.getText().toString()));
//                productTable.setTax(tax);
//                productTable.setProduct_date(update_date);
//                productTable.setImage_product(imagePath);
//            }else{
//                is_upload = false;
//                productTable.setProductId(productId);
//                productTable.setCategoryID(cid);
//                productTable.setLocationId(location_id);
//                productTable.setProductName_eng(binding.updateProductNameEnglish.getText().toString());
//                productTable.setProductName_kh(binding.updateProductNameKhmer.getText().toString());
//                productTable.setProduct_qty(Integer.parseInt(binding.updateProductQty.getText().toString()));
//                productTable.setProduct_barCode(binding.updateProductBarcode.getText().toString());
//                productTable.setProduct_Price(Double.parseDouble(binding.updateProductPrice.getText().toString()));
//                productTable.setProduct_cost(Double.parseDouble(binding.updateProductCost.getText().toString()));
//                productTable.setTax(tax);
//                productTable.setProduct_date(update_date);
//                productTable.setImage_product(imagePath);
//
//            }


//            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
//            blueTeachnology_dao.updateProducts(productTable);
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