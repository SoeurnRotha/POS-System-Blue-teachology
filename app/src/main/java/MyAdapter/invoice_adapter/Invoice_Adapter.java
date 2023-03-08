package MyAdapter.invoice_adapter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DecimalFormat;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Invoice;
import Mydatabase.BlueTeachnology_Database;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Invoice_Adapter extends RecyclerView.Adapter<Invoice_Adapter.ViewInvoice>{


    List<Invoice> invoiceList;

    public Invoice_Adapter(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @NonNull
    @Override
    public ViewInvoice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_invoice, parent, false);

        return new ViewInvoice(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewInvoice holder, @SuppressLint("RecyclerView") int position) {

        holder.invoice_id.setText(String.valueOf(invoiceList.get(position).getInvoiceId()));

        holder.date.setText(String.valueOf(invoiceList.get(position).getInvoice_date()));

        holder.customerName.setText(String.valueOf(invoiceList.get(position).getCustomerName()));

        holder.paymentType.setText(String.valueOf(invoiceList.get(position).getPaymentType()));

        holder.subTotal.setText(numberFormat(String.valueOf(invoiceList.get(position).getGrand_total_dollar())));

        holder.grand_total_khmer.setText(numberFormat(String.valueOf(invoiceList.get(position).getGrand_total_khmer())));

        holder.discount.setText(String.valueOf(invoiceList.get(position).getDiscount()));

        holder.discountAmount.setText(numberFormat(String.valueOf(invoiceList.get(position).getAmount())));


        holder.cashier.setText(String.valueOf(invoiceList.get(position).getQty()));





        holder.invoice_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Delete invoice");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        Toast.makeText(builder.getContext(), "Yes", Toast.LENGTH_SHORT).show();


                        //delete


                        BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deleteInvoiceByid(invoiceList.get(position).getInvoiceId());

                        invoiceList.remove(position);

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


        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension

        String textQrCode =
                        "Order number :" + String.valueOf(invoiceList.get(position).getInvoiceId()) + "\n" +
                        "Date " +String.valueOf(invoiceList.get(position).getInvoice_date()) + "\n" +
                        "Customer : " +String.valueOf(invoiceList.get(position).getCustomerName()) + "\n"+
                         "Payment method :" + String.valueOf(invoiceList.get(position).getPaymentType())
                        +"Cashier" + "\n" + String.valueOf(invoiceList.get(position).getUserId())
                        + "Product name english : " + "\n" + String.valueOf(invoiceList.get(position).getProduct_name_english())
                        + "Product name khmer : "+ "\n" + String.valueOf( invoiceList.get(position).getProduct_name_khmer())
                        + "SubTotal" + "\n" + String.valueOf( invoiceList.get(position).getGrand_total_dollar())
                        + "Discount :" +"\n" + String.valueOf(invoiceList.get(position).getDiscount())
                        + "Discount amount :" +"\n" + String.valueOf( invoiceList.get(position).getAmount())
                        + "Grand Total(Real) : " +"\n" + String.valueOf(invoiceList.get(position).getGrand_total_khmer())
                ;

        QRGEncoder qrgEncoder = new QRGEncoder(textQrCode, null, QRGContents.Type.TEXT, 800);
        holder.image_qr_code.setImageBitmap(qrgEncoder.getBitmap());
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }

    public class ViewInvoice extends RecyclerView.ViewHolder{
        TextView invoice_id, date,customerName,paymentType,cashier,subTotal,discount,discountAmount,grand_total_dollar,grand_total_khmer;
        ImageView image_qr_code;
        ImageButton invoice_delete;
        public ViewInvoice(@NonNull View itemView) {
            super(itemView);

            invoice_id = itemView.findViewById(R.id.invoice_id_order);
            date = itemView.findViewById(R.id.invoice_date_time);
            customerName = itemView.findViewById(R.id.invoice_customer_name);
            paymentType = itemView.findViewById(R.id.invoice_payment_method);
            cashier = itemView.findViewById(R.id.invoice_user_cashier);
            subTotal = itemView.findViewById(R.id.invoice_subtotal);
            discount = itemView.findViewById(R.id.invoice_discount);
            discountAmount = itemView.findViewById(R.id.invoice_discount_amount);

            grand_total_khmer = itemView.findViewById(R.id.invoice_grand_total_real);


            image_qr_code = itemView.findViewById(R.id.invoice_qr_code);

            invoice_delete= itemView.findViewById(R.id.invoice_delete);

        }
    }
    private static String numberFormat(String number){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(Double.parseDouble(number));
    }


}
