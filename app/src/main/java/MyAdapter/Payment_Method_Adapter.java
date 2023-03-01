package MyAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.PaymentMethod.UpdatePaymentMethod;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.PaymentMethod;
import Mydatabase.BlueTeachnology_Database;

public class Payment_Method_Adapter extends RecyclerView.Adapter<Payment_Method_Adapter.PaymentViewHolder>{


    List<PaymentMethod> paymentMethodList;

    public Payment_Method_Adapter(List<PaymentMethod> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_design_payment_method,parent, false);


        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        final PaymentMethod paymentMethod = paymentMethodList.get(position);

        if(paymentMethod ==null){
            return;
        }

        holder.payment_des.setText(String.valueOf(paymentMethodList.get(position).getDecription()));


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), UpdatePaymentMethod.class);
                intent.putExtra("payment_method", paymentMethod);
                view.getContext().startActivity(intent);

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Delete user");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        Toast.makeText(builder.getContext(), "Yes", Toast.LENGTH_SHORT).show();


                        //delete


                        BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deletePayment(paymentMethodList.get(position).getPaymentId());

                        paymentMethodList.remove(position);

                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "No", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder{

        ImageButton delete;
        CardView edit;
        TextView payment_id, payment_des;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

//            payment_id = itemView.findViewById(R.id.payment_method_name);
            payment_des = itemView.findViewById(R.id.payment_method_name);
//
            edit = itemView.findViewById(R.id.card_for_update_payment_method);
            delete = itemView.findViewById(R.id.button_delete_payment);
        }
    }
}
