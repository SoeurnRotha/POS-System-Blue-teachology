package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.Check_out.Cheack_out_cart;
import com.example.bluesystemwithroomdatabase.CreatePDF_for_invoice.CreatePDF;
import com.example.bluesystemwithroomdatabase.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Cheackout;
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

    String getQty;



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


    double discount;
    long khmer_dollar , grand_total, discountAmount;
    int sum;
    BlueTeachnology_Dao blueTeachnology_dao;

    private static final String SHARED_NAME = "blue";
    private static final String KEY_USERNAME = "username";
    @NonNull
    @Override
    public ViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_design_cart, parent,false);

        return new ViewCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCart holder, @SuppressLint("RecyclerView") int position) {
        final CartTable cartTable = cartTableList.get(position);
        holder.peng.setText(String.valueOf(cartTableList.get(position).getProductName_eng()));
        holder.qty.setText(String.valueOf(cartTableList.get(position).getProductQty()));
        holder.price.setText(String.valueOf(cartTableList.get(position).getProductCost()));
        holder.pkh.setText(String.valueOf(cartTableList.get(position).getProductName_kh()));



        holder.qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatePrice();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        updatePrice();

        Glide.with(context).load(cartTable.getProduct_img()).into(holder.imageView);








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

//                int qty =cartTableList.get(position).getProductQty();
                int qty = Integer.parseInt(holder.qty.getText().toString());

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
                int qty = Integer.parseInt(holder.qty.getText().toString());
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

                khmer_dollar = Long.parseLong(khmer_to_dollar.getText().toString());
                grand_total = khmer_dollar * sum;

                discount =0;
                if(discount_input.getText().toString().isEmpty()){
                    totalPrice.setText("$ " + numberFormat(String.valueOf(sum)));

                    totalAmount_khmer.setText("(Real) "+ numberFormat(String.valueOf(grand_total)));

                }else{
                    discount = Double.parseDouble(discount_input.getText().toString());
                    double p = discount /100;
                    discountAmount = (long) (sum - (sum * p));
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
                SharedPreferences sharedPreferences;
                sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Pay to invoice");
                builder.setMessage("Do you want to delete your carts");

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ArrayList<String> engName = new ArrayList<>();
                        ArrayList<String> khName = new ArrayList<>();
                        ArrayList<String> qtyList = new ArrayList<>();
                        ArrayList<String> priceList = new ArrayList<>();
                        for(int k =0 ; k<cartTableList.size() ; k++){
                            engName.add(cartTableList.get(k).getProductName_eng());
                            khName.add(cartTableList.get(k).getProductName_kh());


                            qtyList.add(String.valueOf(cartTableList.get(k).getProductQty()));
                            priceList.add(String.valueOf(cartTableList.get(k).getProductCost()));
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
                            invoice.setQty(qtyList);
                            invoice.setPrice(priceList);
                            invoice.setProduct_name_english(engName);
                            invoice.setProduct_name_khmer(khName);
                            invoice.setCashierName(sharedPreferences.getString(KEY_USERNAME, null));
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
                            invoice.setQty(qtyList);
                            invoice.setPrice(priceList);
                            invoice.setProduct_name_english(engName);
                            invoice.setProduct_name_khmer(khName);
                            invoice.setCashierName(sharedPreferences.getString(KEY_USERNAME, null));
                            invoice.setGrand_total_dollar(sum);
                            invoice.setGrand_total_khmer(grand_total);
                            BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();


                            blueTeachnology_dao.insertInvoice(invoice);
                            //delete all products
                            deleteRecordCart(cartTableList);

                        }



                        //cheack out
//                        Intent intent = new Intent(view.getContext(), Cheack_out_cart.class);
//                        ArrayList<String> engName = new ArrayList<>();
//                        ArrayList<String> khName = new ArrayList<>();
//                        ArrayList<String> qtyList = new ArrayList<>();
//                        ArrayList<String> priceList = new ArrayList<>();
//                        ArrayList<String> amountList = new ArrayList<>();
//                        for(int k =0 ; k<cartTableList.size() ; k++){
//                            engName.add(cartTableList.get(k).getProductName_eng());
//                            khName.add(cartTableList.get(k).getProductName_kh());
//
//
//                            qtyList.add(String.valueOf(cartTableList.get(k).getProductQty()));
//                            priceList.add(String.valueOf(cartTableList.get(k).getProductCost()));
//                            amountList.add(String.valueOf(cartTableList.get(k).getProductQty() * cartTableList.get(k).getProductCost()));
//
//
//
//
//                        }
//
//                        int sumtotal =0;
//                        for(int y=0;  y< cartTableList.size(); y++){
//
//                            sumtotal += (cartTableList.get(y).getProductCost() * cartTableList.get(y).getProductQty());
//
//                        }


                        //check out

//                        Cheackout cheackout = new Cheackout();
//                        cheackout.setQty(qtyList);
//                        cheackout.setPrice(priceList);
//                        cheackout.setAmount(amountList);
//                        cheackout.setTotal_dollar(sumtotal);
//                        cheackout.setName(engName);
//
//                        blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();
//                        blueTeachnology_dao.insertCheackout(cheackout);
//                        deleteRecordCart(cartTableList);

                        Intent intent = new Intent(view.getContext() , CreatePDF.class);

                        view.getContext().startActivity(intent);






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

        TextView peng,pkh,price;

        TextInputEditText qty;

        ImageButton delete, remove, add;
        ImageView imageView;
        public ViewCart(@NonNull View itemView) {
            super(itemView);

            peng = itemView.findViewById(R.id.product_name_eng);
            pkh = itemView.findViewById(R.id.product_name_kh);
            qty = itemView.findViewById(R.id.count_qty);
            price = itemView.findViewById(R.id.product_price);
            delete = itemView.findViewById(R.id.button_cart_delete);
            remove = itemView.findViewById(R.id.button_remove_qty);
            add = itemView.findViewById(R.id.button_add_qty);

            imageView = itemView.findViewById(R.id.image_cart);


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
                if(cartTableList.size() >0){
                    cartTableList.clear();

                }
            }


        }
        DeleteAllRecordTask deleteAllRecordTask = new DeleteAllRecordTask();
        deleteAllRecordTask.execute();


    }


}
