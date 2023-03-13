package com.example.bluesystemwithroomdatabase.Pos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Cart.Cart;
import com.example.bluesystemwithroomdatabase.databinding.ActivityPosBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Model.ProductTable;
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
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();


        //show data from category
        categoryTableList = blueTeachnology_dao.getAllCateroy();
//        Pos_category_adapter_show_list_view adapter_show_list_category = new Pos_category_adapter_show_list_view(categoryTableList);
//        binding.listCategoryPos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        binding.listCategoryPos.setAdapter(adapter_show_list_category);

        onTab();
        //tab


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



        //search
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

        binding.addCartPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pos.this, Cart.class);
                startActivity(intent);
            }
        });
    }

    private void onTab(){
        binding.listCategoryPos.addTab(binding.listCategoryPos.newTab().setText("All"));
        if(categoryTableList.size() !=0){
            for(CategoryTable categoryTable: categoryTableList){
                categoryName = categoryTable.getCategoryname_Eng();
                binding.listCategoryPos.addTab(binding.listCategoryPos.newTab().setText(categoryName));
            }

        }
        binding.listCategoryPos.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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