package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Customer;
import Model.Invoice;
import Model.PaymentMethod;
import Mydatabase.BlueTeachnology_Database;

public class Cart_Adater extends RecyclerView.Adapter<Cart_Adater.ViewCart> {

    List<CartTable>  cartTableList;
    TextView subtotal, totalPrice,totalAmount_khmer;

    TextInputEditText discount_input;
    TextInputEditText khmer_to_dollar;

    Button submit,paynow;

    Context context;

    String customerName,PaymentType;
    Spinner selectCustomerName,selectPaymenyType;

    List<PaymentMethod> paymentMethodList;
    List<Customer> customerList;


    public Cart_Adater(List<CartTable> cartTableList, TextView subtotal, TextView totalPrice, TextInputEditText discount_input,Button submit,TextInputEditText khmer_to_dollar,TextView totalAmount_khmer,Button paynow, Context context, Spinner selectCustomerName, Spinner selectPaymenyType) {
        this.cartTableList = cartTableList;
        this.subtotal = subtotal;
        this.totalPrice = totalPrice;
        this.discount_input = discount_input;
        this.submit = submit;
        this.khmer_to_dollar = khmer_to_dollar;
        this.totalAmount_khmer = totalAmount_khmer;
        this.paynow = paynow ;
        this.context = context;

        this.selectCustomerName = selectCustomerName;
        this.selectPaymenyType = selectPaymenyType;

    }


    double khmer_dollar,discount , grand_total, discountAmount;
    int sum;
    BlueTeachnology_Dao blueTeachnology_dao;
    @NonNull
    @Override
    public ViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_design_cart, parent,false);

        return new ViewCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCart holder, @SuppressLint("RecyclerView") int position) {
        holder.peng.setText(String.valueOf(cartTableList.get(position).getProductName_eng()));
        holder.qty.setText(String.valueOf(cartTableList.get(position).getProductQty()));
        holder.price.setText(String.valueOf(cartTableList.get(position).getProductCost()));
        holder.pkh.setText(String.valueOf(cartTableList.get(position).getProductName_kh()));





        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to delete your carts");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deleteCartByid(cartTableList.get(position).getCartId());
                        cartTableList.remove(position);
                        notifyDataSetChanged();
                        updatePrice();

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



        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty =cartTableList.get(position).getProductQty();
                if(qty >=1){
                    qty--;
                    cartTableList.get(position).setProductQty(qty);
                    notifyDataSetChanged();
                    updatePrice();
                }


            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = cartTableList.get(position).getProductQty();
                qty++;
                cartTableList.get(position).setProductQty(qty);
                notifyDataSetChanged();
                updatePrice();

            }
        });


        blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();
        
        customerList = blueTeachnology_dao.getAllCustomer();
        paymentMethodList = blueTeachnology_dao.getAllPayments();


        Payment_method_bastAdapter payment_method_bastAdapter = new Payment_method_bastAdapter(context,paymentMethodList);
        Customer_bastAdapter customer_bastAdapter = new Customer_bastAdapter(context, customerList);


        selectCustomerName.setAdapter(customer_bastAdapter);
        selectPaymenyType.setAdapter(payment_method_bastAdapter);


        
        selectCustomerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                customerName = customerList.get(i).getCustomerName();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectPaymenyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentType = paymentMethodList.get(i).getDecription();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sum =0;
                for(int i=0;  i< cartTableList.size(); i++){

                    sum += (cartTableList.get(i).getProductCost() * cartTableList.get(i).getProductQty());

                }

                khmer_dollar = Double.parseDouble(khmer_to_dollar.getText().toString());
                grand_total = khmer_dollar * sum;

                discount =0;
                if(discount_input.getText().toString().isEmpty()){
                    totalPrice.setText("$ " + numberFormat(String.valueOf(sum)));

                    totalAmount_khmer.setText("(Real) "+ numberFormat(String.valueOf(grand_total)));

                }else{
                    discount = Double.parseDouble(discount_input.getText().toString());
                    double p = discount /100;
                    discountAmount = sum - (sum * p);
                    Toast.makeText(submit.getContext(), "discountAmount = " + discountAmount, Toast.LENGTH_SHORT).show();
                    totalPrice.setText("$ " + numberFormat(String.valueOf(discountAmount)));

                    grand_total = khmer_dollar * discountAmount;



                    totalAmount_khmer.setText("(Real) "+ numberFormat(String.valueOf(grand_total)));
                }


            }
        });

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Pay to invoice");
                builder.setMessage("Do you want to delete your carts");

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ArrayList<String> engName = new ArrayList<>();
                        ArrayList<String> khName = new ArrayList<>();

                        for(int k =0 ; k<cartTableList.size() ; k++){
                            engName.add(cartTableList.get(k).getProductName_eng());
                            khName.add(cartTableList.get(k).getProductName_kh());
                        }


                        if(discountAmount != 0){




                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, 1);
                            System.out.println(cal.getTime());


                            Invoice invoice = new Invoice();
                            invoice.setInvoice_date(String.valueOf(cal.getTime()));

                            invoice.setCustomerName(customerName);
                            invoice.setPaymentType(PaymentType);
                            invoice.setAmount(discountAmount );
                            invoice.setDiscount(discount);

                            invoice.setProduct_name_english(String.valueOf(engName));
                            invoice.setProduct_name_khmer(String.valueOf(khName));
                            invoice.setUserId(1);
                            invoice.setGrand_total_dollar(discountAmount);
                            invoice.setGrand_total_khmer(grand_total);
                            BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();


                            blueTeachnology_dao.insertInvoice(invoice);
                            //delete all products
                            deleteRecordCart(cartTableList);

                        }else{
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, 1);
                            System.out.println(cal.getTime());


                            Invoice invoice = new Invoice();
                            invoice.setInvoice_date(String.valueOf(cal.getTime()));

                            invoice.setCustomerName(customerName);
                            invoice.setPaymentType(PaymentType);
                            invoice.setAmount(discountAmount );
                            invoice.setDiscount(discount);
                            invoice.setProduct_name_english(String.valueOf(engName));
                            invoice.setProduct_name_khmer(String.valueOf(khName));



                            invoice.setUserId(1);
                            invoice.setGrand_total_dollar(sum);
                            invoice.setGrand_total_khmer(grand_total);
                            BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();


                            blueTeachnology_dao.insertInvoice(invoice);
                            //delete all products
                            deleteRecordCart(cartTableList);

                        }



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


            }
        });

    }

    @Override
    public int getItemCount() {
        return cartTableList.size();
    }

    public class ViewCart extends RecyclerView.ViewHolder{

        TextView peng,pkh,qty,price;

        ImageButton delete, remove, add;
        public ViewCart(@NonNull View itemView) {
            super(itemView);

            peng = itemView.findViewById(R.id.product_name_eng);
            pkh = itemView.findViewById(R.id.product_name_kh);

            qty = itemView.findViewById(R.id.count_qty);

            price = itemView.findViewById(R.id.product_price);
            delete = itemView.findViewById(R.id.button_cart_delete);
            remove = itemView.findViewById(R.id.button_remove_qty);
            add = itemView.findViewById(R.id.button_add_qty);
        }
    }


    //for update price  * qty
    public void updatePrice(){
        int sum =0;
        for(int i=0;  i< cartTableList.size(); i++){

            sum += (cartTableList.get(i).getProductCost() * cartTableList.get(i).getProductQty());

        }
        subtotal.setText("$" + sum);

    }

    private static String numberFormat(String number){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(Double.parseDouble(number));
    }

    public void deleteRecordCart(List<CartTable> cartTableList){
        class DeleteAllRecordTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();


                blueTeachnology_dao.deleteAllCart(cartTableList);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
//                Toast.makeText(context, "Delete all Record", Toast.LENGTH_SHORT).show();

                if(cartTableList.size() >0){
                    cartTableList.clear();

                }
            }


        }
        DeleteAllRecordTask deleteAllRecordTask = new DeleteAllRecordTask();
        deleteAllRecordTask.execute();


    }


}
