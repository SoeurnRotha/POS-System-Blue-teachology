package com.example.bluesystemwithroomdatabase.Pos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.databinding.ActivityPosBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Model.ProductTable;
import MyAdapter.pos_adapter.Pos_category_adapter_show_list_view;
import MyAdapter.pos_adapter.Product_Base_Adapter_gridview;
import Mydatabase.BlueTeachnology_Database;
import Relationship.ManyToMany.CategoryWithProduct;

public class Pos extends AppCompatActivity {


    ActivityPosBinding binding;

    BlueTeachnology_Dao blueTeachnology_dao;
    List<CategoryTable> categoryTableList;


    List<CategoryWithProduct> categoryWithProducts;
    List<ProductTable> productTableList;
    Product_Base_Adapter_gridview product_base_adapter_gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();


        //show data from category
        categoryTableList = blueTeachnology_dao.getAllCateroy();
        Pos_category_adapter_show_list_view adapter_show_list_category = new Pos_category_adapter_show_list_view(categoryTableList);
        binding.listCategoryPos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.listCategoryPos.setAdapter(adapter_show_list_category);



        //show data from products

        productTableList = blueTeachnology_dao.getAllProducts();
        product_base_adapter_gridview = new Product_Base_Adapter_gridview(productTableList, getApplicationContext());
        binding.gridviewItemCategoryWithProductManyToMany.setAdapter(product_base_adapter_gridview);





        binding.gridviewItemCategoryWithProductManyToMany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        binding.icon.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });


        binding.searchProducts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                product_base_adapter_gridview.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
                binding.searchProducts.setText(intentResult.getContents());

            }
        }
    }


}