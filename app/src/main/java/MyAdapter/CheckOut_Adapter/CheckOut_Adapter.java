package MyAdapter.CheckOut_Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import Model.Cheackout;

public class CheckOut_Adapter extends RecyclerView.Adapter<CheckOut_Adapter.ViewCheckout>{


    List<Cheackout> cheackoutList;
    ArrayList<String> arrayListQTY ;
    ArrayList<String> arrayListPrice;
    ArrayList<String> arrayListAmount;
    ArrayList<String> arrayListDescription;
    ArrayList<String> arrayListNo;

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

        arrayListQTY = new ArrayList<>();
        arrayListQTY = cheackoutList.get(position).getQty();

        arrayListPrice = new ArrayList<>();
        arrayListPrice = cheackoutList.get(position).getPrice();


        arrayListAmount = new ArrayList<>();
        arrayListAmount = cheackoutList.get(position).getAmount();

        arrayListDescription = new ArrayList<>();
        arrayListDescription = cheackoutList.get(position).getName();

        arrayListNo = new ArrayList<>();
        arrayListNo = cheackoutList.get(position).getName();


        String storeQTY = "";
        String storePRICE = "";
        String storeAMOUNT = "";
        String storeDESCRIPTION = "";
        String storeNo = "";
        for (int i=0;i<arrayListQTY.size(); i++){
            storeQTY += "" + arrayListQTY.get(i) +"\n";
        }

        for (int i=0;i<arrayListPrice.size(); i++){
            storePRICE += "" + arrayListPrice.get(i) +"\n";
        }

        for (int i=0;i<arrayListAmount.size(); i++){
            storeAMOUNT += "" + arrayListAmount.get(i) +"\n";
        }
        for (int i=0;i<arrayListDescription.size(); i++){
            storeDESCRIPTION += "" + arrayListDescription.get(i) +"\n";

        }

        for (int i=1; i<=arrayListNo.size() ;i++){
            storeNo += i +"\n";
        }

        holder.no.setText(String.valueOf(storeNo));
        holder.qty.setText(String.valueOf(storeQTY));
        holder.description.setText(String.valueOf(storeDESCRIPTION));
        holder.price.setText(String.valueOf(storePRICE));
        holder.amount.setText(String.valueOf(storeAMOUNT));






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
