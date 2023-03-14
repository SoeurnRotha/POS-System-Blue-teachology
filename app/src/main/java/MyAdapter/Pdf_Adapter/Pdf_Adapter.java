package MyAdapter.Pdf_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import Model.Cheackout;

public class Pdf_Adapter extends RecyclerView.Adapter<Pdf_Adapter.ViewPdf>{


    List<Cheackout> cheackoutList;
    Context context;

    ArrayList<String> arrayListQTY ;
    ArrayList<String> arrayListPrice;
    ArrayList<String> arrayListAmount;
    ArrayList<String> arrayListDescription;
    ArrayList<String> arrayListNo;


    TextView pdfCashier, subtotal,grandTotalDollar,grandTotalKhmer, convertKhmerTodollar;


    public Pdf_Adapter(List<Cheackout> cheackoutList, Context context, TextView pdfCashier, TextView subtotal, TextView grandTotalDollar, TextView grandTotalKhmer, TextView convertKhmerTodollar) {
        this.cheackoutList = cheackoutList;
        this.context = context;
        this.pdfCashier =pdfCashier;
        this.subtotal = subtotal;
        this.grandTotalDollar = grandTotalDollar;
        this.grandTotalKhmer = grandTotalKhmer;
        this.convertKhmerTodollar = convertKhmerTodollar;
    }

    @NonNull
    @Override
    public ViewPdf onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_receipt,parent,false);
        return new ViewPdf(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPdf holder, int position) {

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



        convertKhmerTodollar.setText(String.valueOf(cheackoutList.get(position).getConverDollar_to_khmer()));
        grandTotalDollar.setText(String.valueOf(cheackoutList.get(position).getTotal_dollar()));
        grandTotalKhmer.setText(String.valueOf(cheackoutList.get(position).getTotal_khmer()));
        subtotal.setText(String.valueOf(cheackoutList.get(position).get));





    }

    @Override
    public int getItemCount() {
        return cheackoutList.size();
    }

    public class ViewPdf extends RecyclerView.ViewHolder {
        TextView qty,price,amount,total_dollar,total_khmer,description,no;

        public ViewPdf(@NonNull View itemView) {
            super(itemView);

            qty = itemView.findViewById(R.id.receipt_qty);
            price = itemView.findViewById(R.id.receipt_price);
            amount = itemView.findViewById(R.id.receipt_amount);
            description = itemView.findViewById(R.id.receipt_desctiption);
            no = itemView.findViewById(R.id.receipt_no);

        }
    }

}
