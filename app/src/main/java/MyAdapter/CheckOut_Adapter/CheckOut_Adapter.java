package MyAdapter.CheckOut_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

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

        holder.qty.setText((CharSequence) cheackoutList.get(position).getQty());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewCheckout extends RecyclerView.ViewHolder {
        TextView qty,price,amount,total_dollar,total_khmer,description;
        ImageView delete;
        public ViewCheckout(@NonNull View itemView) {
            super(itemView);

            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            description = itemView.findViewById(R.id.desctiption);

            delete = itemView.findViewById(R.id.delete);
        }
    }
}
