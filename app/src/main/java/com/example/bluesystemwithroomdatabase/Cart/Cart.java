package com.example.bluesystemwithroomdatabase.Cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.DeleteTable;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCartBinding;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Customer;
import Model.Invoice;
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

    String customerName, PaymentType;
    BlueTeachnology_Dao blueTeachnology_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        List<CartTable> cartTableList = blueTeachnology_dao.getAllCart();


        Cart_Adater adater = new Cart_Adater(
                cartTableList, binding.showSubtotal,
                binding.totalAmount,
                binding.inputDiscount,
                binding.discountAmount,
                binding.inputKhmerToDollar,
                binding.totalAmountKhmer,
                binding.cartPaynow,
                getApplicationContext(),
                binding.selectCustomer,
                binding.selectPaymentMethod

        );
        binding.listCart.setLayoutManager(new LinearLayoutManager(this));
        binding.listCart.setAdapter(adater);

    }


    public void deleteRecordCart(List<CartTable> cartTableList){
        class DeleteAllRecordTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();


                blueTeachnology_dao.deleteAllCart(cartTableList);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(Cart.this, "Delete all Record", Toast.LENGTH_SHORT).show();

                if(cartTableList.size() >0){
                    cartTableList.clear();

                }
            }


        }
        DeleteAllRecordTask deleteAllRecordTask = new DeleteAllRecordTask();
        deleteAllRecordTask.execute();


    }


}



