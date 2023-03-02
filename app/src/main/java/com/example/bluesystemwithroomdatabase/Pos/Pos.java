package com.example.bluesystemwithroomdatabase.Pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.bluesystemwithroomdatabase.databinding.ActivityPosBinding;

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
        Product_Base_Adapter_gridview adapter_gridview = new Product_Base_Adapter_gridview(productTableList, getApplicationContext());
        binding.gridviewItemCategoryWithProductManyToMany.setAdapter(adapter_gridview);





        binding.gridviewItemCategoryWithProductManyToMany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}