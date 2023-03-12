package com.example.bluesystemwithroomdatabase.Products;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.databinding.ActivityUpdateProductsBinding;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Model.LocationTable;
import Model.ProductTable;
import MyAdapter.Location_Bast_Adapter;
import MyAdapter.Select_Adapter_base_category;
import Mydatabase.BlueTeachnology_Database;

public class Update_products extends AppCompatActivity {

    ActivityUpdateProductsBinding binding;
    int cid;
    int location_id;
    Uri uri;

    BlueTeachnology_Dao blueTeachnology_dao;
    boolean IS_UPDATE = false;

    File file;


    boolean is_upload = false;
    String imagePath;

    int productId;

    ProductTable productTable;
    List<CategoryTable> categoryTableList;

    List<LocationTable> locationTableList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //show data for select category

        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        categoryTableList = blueTeachnology_dao.getAllCateroy();
        Select_Adapter_base_category adapter = new Select_Adapter_base_category(categoryTableList, getApplicationContext());
        binding.updateCategorySelectItemInProducts.setAdapter(adapter);



        //show data for select location
        locationTableList = blueTeachnology_dao.getAllLocation();
        Location_Bast_Adapter location_bast_adapter = new Location_Bast_Adapter(locationTableList, getApplicationContext());
        binding.updateInventorySelectItemInProducts.setAdapter(location_bast_adapter);


        getInten();

        binding.updateCategorySelectItemInProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cid = categoryTableList.get(i).getCategoryID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.updateInventorySelectItemInProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location_id = locationTableList.get(i).getLocation_Id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.updateProductSaveNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                save(view);
            }
        });


        binding.selectImageProductsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_upload = true;
                getImage();
            }
        });

    }

    public void getInten(){
        if(getIntent().hasExtra("products")){
            IS_UPDATE = true;
            binding.updateProductSaveNow.setText("UPDATE PRODUCT");

            productTable = (ProductTable) getIntent().getSerializableExtra("products");
            String product_cost = String.valueOf(productTable.getProduct_cost());
            String product_price = String.valueOf(productTable.getProduct_Price());
            String product_qty = String.valueOf(productTable.getProduct_qty());
            String product_tax = String.valueOf(productTable.getTax());

            binding.updateProductNameEnglish.setText(productTable.getProductName_eng());
            binding.updateProductNameKhmer.setText(productTable.getProductName_kh());
            binding.updateProductQty.setText(product_qty);
            binding.updateProductPrice.setText(product_price);
            binding.updateProductBarcode.setText(productTable.getProduct_barCode());

            binding.updateProductCost.setText(product_cost);
            binding.updateProductTax.setText(product_tax);
            //show image
            Glide.with(this).load(productTable.getImage_product()).into(binding.showImageProductsForUpdate);
            productId = productTable.getProductId();
            imagePath = productTable.getImage_product();
            Toast.makeText(this, ""+imagePath, Toast.LENGTH_SHORT).show();


        }else {
            IS_UPDATE = false;
            binding.updateProductSaveNow.setText("SAVE");
        }

    }


    public void save(View view){
        if(IS_UPDATE == false){
            SaveProducts saveCustomer = new SaveProducts();
            saveCustomer.execute();
        }else{
            UpdateProducts updateProducts = new UpdateProducts();
            updateProducts.execute();
        }
    }


    class SaveProducts extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            productTable.setCategoryID(cid);
            productTable.setLocationId(location_id);
            productTable.setProduct_cost(Double.parseDouble(binding.updateProductCost.getText().toString()));
            productTable.setProduct_qty(Integer.parseInt(binding.updateProductQty.getText().toString()));
            productTable.setProduct_Price(Double.parseDouble(binding.updateProductPrice.getText().toString()));
            productTable.setProduct_barCode(binding.updateProductBarcode.getText().toString());
            productTable.setProductName_eng(binding.updateProductNameEnglish.getText().toString());
            productTable.setProductName_kh(binding.updateProductNameKhmer.getText().toString());
            productTable.setTax(Double.parseDouble(binding.updateProductTax.getText().toString()));


            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertProducts(productTable);




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


            double tax = Double.parseDouble(binding.updateProductTax.getText().toString());

            ProductTable productTable = new ProductTable();
            if(is_upload == true){
                productTable.setProductId(productId);
                productTable.setCategoryID(cid);
                productTable.setLocationId(location_id);

                productTable.setProductName_eng(binding.updateProductNameEnglish.getText().toString());
                productTable.setProductName_kh(binding.updateProductNameKhmer.getText().toString());
                productTable.setProduct_qty(Integer.parseInt(binding.updateProductQty.getText().toString()));
                productTable.setProduct_barCode(binding.updateProductBarcode.getText().toString());
                productTable.setProduct_Price(Double.parseDouble(binding.updateProductPrice.getText().toString()));
                productTable.setProduct_cost(Double.parseDouble(binding.updateProductCost.getText().toString()));
                productTable.setTax(tax);
                productTable.setProduct_date(update_date);
                productTable.setImage_product(imagePath);
            }else{
                is_upload = false;
                productTable.setProductId(productId);
                productTable.setCategoryID(cid);
                productTable.setLocationId(location_id);
                productTable.setProductName_eng(binding.updateProductNameEnglish.getText().toString());
                productTable.setProductName_kh(binding.updateProductNameKhmer.getText().toString());
                productTable.setProduct_qty(Integer.parseInt(binding.updateProductQty.getText().toString()));
                productTable.setProduct_barCode(binding.updateProductBarcode.getText().toString());
                productTable.setProduct_Price(Double.parseDouble(binding.updateProductPrice.getText().toString()));
                productTable.setProduct_cost(Double.parseDouble(binding.updateProductCost.getText().toString()));
                productTable.setTax(tax);
                productTable.setProduct_date(update_date);
                productTable.setImage_product(imagePath);

            }


            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.updateProducts(productTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            finish();
            Toast.makeText(Update_products.this, "Update successful", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearText(){

        binding.updateProductNameEnglish.setText("");
        binding.updateProductNameKhmer.setText("");
        binding.updateProductTax.setText("");
        binding.updateProductCost.setText("");
        binding.updateProductPrice.setText("");
        binding.updateProductBarcode.setText("");
        binding.updateProductQty.setText("");

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
                    binding.showImageProductsForUpdate.setImageURI(uri);

                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });

}