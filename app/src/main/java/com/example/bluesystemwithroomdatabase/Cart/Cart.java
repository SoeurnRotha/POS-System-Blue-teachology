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
    BlueTeachnology_Dao blueTeachnology_dao;

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
        Customer_bastAdapter adapterbast = new Customer_bastAdapter(getApplicationContext(), customers);

        binding.selectCustomer.setAdapter(adapterbast);
        binding.selectPaymentMethod.setAdapter(payment_method_bastAdapter);


        Cart_Adater adater = new Cart_Adater(
                cartTableList, binding.showSubtotal,
                binding.totalAmount,
                binding.inputDiscount,
                binding.discountAmount,
                binding.inputKhmerToDollar,
                binding.totalAmountKhmer,
                binding.cartPaynow,
                getApplicationContext()
        );
        binding.listCart.setLayoutManager(new LinearLayoutManager(this));
        binding.listCart.setAdapter(adater);


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




        binding.cartPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Pay to invoice");
                builder.setMessage("Do you want to delete your carts");
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You pay successful", Toast.LENGTH_SHORT).show();


                        //delete all products
                        deleteRecordCart(cartTableList);
                        recreate();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
                deleteRecordCart(cartTableList);
            }
        });


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



