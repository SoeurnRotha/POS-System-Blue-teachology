package com.example.bluesystemwithroomdatabase.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCartBinding;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Customer;
import Model.PaymentMethod;
import MyAdapter.Cart_Adater;
import MyAdapter.Customer_bastAdapter;
import MyAdapter.Payment_method_bastAdapter;
import Mydatabase.BlueTeachnology_Database;
import Relationship.ManyToMany.OneToOne.ProductWithCarts;

public class Cart extends AppCompatActivity {


    ActivityCartBinding binding;
    
    String paymentDes;
    String customerDes;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        List<CartTable> cartTableList = blueTeachnology_dao.getAllCart();
        List<ProductWithCarts> productWithCarts = blueTeachnology_dao.getProductWithCarts();
        List<Customer> customers = blueTeachnology_dao.getAllCustomer();
        List<PaymentMethod> paymentMethodList = blueTeachnology_dao.getAllPayments();




        Payment_method_bastAdapter payment_method_bastAdapter = new Payment_method_bastAdapter(getApplicationContext(), paymentMethodList);
        Customer_bastAdapter adapterbast = new Customer_bastAdapter(getApplicationContext(),customers);

        binding.selectCustomer.setAdapter(adapterbast);
        binding.selectPaymentMethod.setAdapter(payment_method_bastAdapter);






        Cart_Adater adater = new Cart_Adater(cartTableList,binding.showSubtotal,binding.totalAmount, binding.inputDiscount,binding.submit);
        binding.listCart.setLayoutManager(new LinearLayoutManager(this));
        binding.listCart.setAdapter(adater);







//        int sum = 0;
//        for(int i=0;i< cartTableList.size(); i++){
//            sum += cartTableList.get(i).getProductPrice() * cartTableList.get(i).getProductQty();
//
//
//        }
//        binding.showSubtotal.setText("$"+ sum);



        binding.selectCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                customerDes = customers.get(i).getCustomerName();
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.selectPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                paymentDes = paymentMethodList.get(i).getDecription();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}



