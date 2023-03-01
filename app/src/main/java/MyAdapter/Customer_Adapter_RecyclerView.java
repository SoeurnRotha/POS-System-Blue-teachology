package MyAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.Customer.UpdateCustomer;
import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Mydatabase.BlueTeachnology_Database;

public class Customer_Adapter_RecyclerView extends RecyclerView.Adapter<Customer_Adapter_RecyclerView.CustomerViewHolder> {


    List<Customer> customerList;
    Context context;

    public Customer_Adapter_RecyclerView(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        final Customer customer = customerList.get(position);

        if(customer == null){
            return;
        }


        holder.name.setText(String.valueOf(customer.getCustomerName()));
        holder.address.setText(String.valueOf(customer.getCustomer_address()));
        holder.phone.setText(String.valueOf(customer.getCustomer_phone()));
        holder.date.setText(String.valueOf(customer.getDate_time_create()));
        holder.email.setText(String.valueOf(customer.getCustomer_email()));


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateCustomer.class);
                intent.putExtra("customer",customer);
                view.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Customer ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete

                        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deleteCustomerByid(customerList.get(position).getCustomerId());

                        customerList.remove(position);
                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    Toast.makeText(builder.getContext(), "No", Toast.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(customerList != null){
            return customerList.size();
        }
        return 0;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, phone, email, date;
        ImageView delete;

        CardView update;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_customer);
            address = itemView.findViewById(R.id.address_customer);
            phone = itemView.findViewById(R.id.phone_customer);
            email = itemView.findViewById(R.id.email_customer);
            date = itemView.findViewById(R.id.date_create_customer);


            delete = itemView.findViewById(R.id.delete_customer);
            update = itemView.findViewById(R.id.update_customer_card_view);

        }
    }

}
