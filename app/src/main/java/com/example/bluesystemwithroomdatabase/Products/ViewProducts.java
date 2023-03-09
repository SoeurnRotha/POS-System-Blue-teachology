package com.example.bluesystemwithroomdatabase.Products;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewProductsBinding;
import com.example.bluesystemwithroomdatabase.databinding.CustomDialogDoneForAddProductBinding;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Model.Customer;
import Model.LocationTable;
import Model.ProductTable;
import MyAdapter.Location_Bast_Adapter;
import MyAdapter.Product_Adapter;
import MyAdapter.Select_Adapter_base_category;
import Mydatabase.BlueTeachnology_Database;




public class ViewProducts extends AppCompatActivity {

    ActivityViewProductsBinding binding;
    List<ProductTable> productTableList;

    List<LocationTable> locationTableList;

    int location_id;


    Product_Adapter adapter;

    BlueTeachnology_Dao blueTeachnology_dao;
    int categoryId;
    int SELECT_PICTURE= 200;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddProduct.setVisibility(view.VISIBLE);
                binding.viewproducts.setVisibility(view.GONE);
            }
        });

        binding.backViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showAddProduct.setVisibility(view.GONE);
                binding.viewproducts.setVisibility(view.VISIBLE);
            }
        });

        //scan code
        binding.barcode.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scanCode();
            }
        });


        Show_Products();

        //show data category
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        List<CategoryTable> categoryTableList = blueTeachnology_dao.getAllCateroy();
        Select_Adapter_base_category adapter_base_category = new Select_Adapter_base_category(categoryTableList,getBaseContext());
        binding.selectCategoryName.setAdapter(adapter_base_category);



        //show data inventory
        locationTableList = blueTeachnology_dao.getAllLocation();
        Location_Bast_Adapter location_bast_adapter = new Location_Bast_Adapter(locationTableList, getBaseContext());
        binding.selectLocationName.setAdapter(location_bast_adapter);



        binding.selectCategoryName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryId = categoryTableList.get(i).getCategoryID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });



        //select item save inventory
        binding.selectLocationName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location_id = locationTableList.get(i).getLocation_Id();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.saveProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveProuducts saveProuducts= new SaveProuducts();
                saveProuducts.execute();

            }
        });


        binding.selectImageProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });




    }


    public void clearText(){
        binding.inputProductNameEnglish.setText("");
        binding.inputProductNameKhmer.setText("");
        binding.inputProductQty.setText("");
        binding.inputProductPrice.setText("");
        binding.inputProductBarcode.setText("");
        binding.inputProductCost.setText("");
        binding.inputProductTax.setText("");
    }

    public void done()
    {
        CustomDialogDoneForAddProductBinding addProductBinding = CustomDialogDoneForAddProductBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewProducts.this);
        builder.setView(addProductBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();

        addProductBinding.okDoneProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void Show_Products(){
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        productTableList = blueTeachnology_dao.getProductWithCategory();
        adapter = new Product_Adapter(productTableList, getApplicationContext() );
        binding.listviewProducts.setLayoutManager(new LinearLayoutManager(this));
        binding.listviewProducts.setAdapter(adapter);






    }
    public void scanCode(){

        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setPrompt("Scan code");
        intentIntegrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if(intentResult != null){
            if(intentResult.getContents() == null){
                Toast.makeText(this, "No code", Toast.LENGTH_SHORT).show();
            }else{
                binding.inputProductBarcode.setText(intentResult.getContents());

            }
        }
    }

    class SaveProuducts extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String date_create = simpleDateFormat.format(date);


            String pname_eng,pname_kh;
            double tax = Double.parseDouble(binding.inputProductTax.getText().toString());
            double resultTax = tax/100;


            pname_eng = binding.inputProductNameEnglish.getText().toString();
            pname_kh = binding.inputProductNameKhmer.getText().toString();
            ProductTable productTable = new ProductTable();

            productTable.setProductName_eng(pname_eng);
            productTable.setProductName_kh(pname_kh);

            productTable.setCategoryID(categoryId);
            productTable.setLocationId(location_id);


            productTable.setProduct_qty(Integer.parseInt(binding.inputProductQty.getText().toString()));
            productTable.setProduct_Price(Double.parseDouble(binding.inputProductPrice.getText().toString()));
            productTable.setProduct_cost(Double.parseDouble(binding.inputProductCost.getText().toString()));
            productTable.setProduct_barCode(binding.inputProductBarcode.getText().toString());
            productTable.setTax(resultTax);
            productTable.setProduct_date(date_create);


            productTable.setImage_product(file.getPath());

            blueTeachnology_dao.insertProducts(productTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            clearText();
            done();
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

                    binding.selectImageProducts.setImageURI(uri);
                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    Toast.makeText(this, "No image pick", Toast.LENGTH_SHORT).show();
                }
            });
}