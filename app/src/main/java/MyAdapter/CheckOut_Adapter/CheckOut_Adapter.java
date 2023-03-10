package MyAdapter.CheckOut_Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import Model.Cheackout;

public class CheckOut_Adapter extends RecyclerView.Adapter<CheckOut_Adapter.ViewCheckout>{


    List<Cheackout> cheackoutList;

    public CheckOut_Adapter(List<Cheackout> cheackoutList) {
        this.cheackoutList = cheackoutList;
    }

    @NonNull
    @Override
    public ViewCheckout onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_cheackout,parent,false);
        return new ViewCheckout(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCheckout holder, int position) {
        holder.qty.setText(String.valueOf(cheackoutList.get(position).getQty()));
        holder.description.setText(String.valueOf(cheackoutList.get(position).getName()));
        holder.price.setText(String.valueOf(cheackoutList.get(position).getPrice()));
        holder.amount.setText(String.valueOf(cheackoutList.get(position).getAmount()));
        holder.no.setText(String.valueOf(cheackoutList.get(position).getCheackoutId()));


    }

    @Override
    public int getItemCount() {
        return cheackoutList.size();
    }

    class ViewCheckout extends RecyclerView.ViewHolder {
        TextView qty,price,amount,total_dollar,total_khmer,description,no;
        ImageView delete;
        public ViewCheckout(@NonNull View itemView) {
            super(itemView);

            qty = itemView.findViewById(R.id.list_qty);
            price = itemView.findViewById(R.id.list_price);
            amount = itemView.findViewById(R.id.list_amount);
            description = itemView.findViewById(R.id.list_desctiption);
            no = itemView.findViewById(R.id.list_no);

            delete = itemView.findViewById(R.id.list_delete);
        }
    }
}
