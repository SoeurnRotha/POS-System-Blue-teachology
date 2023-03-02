package MyAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.bluesystemwithroomdatabase.Cart.Cart;
import com.example.bluesystemwithroomdatabase.R;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Mydatabase.BlueTeachnology_Database;

public class Cart_Adater extends RecyclerView.Adapter<Cart_Adater.ViewCart> {

    List<CartTable>  cartTableList;
    TextView subtotal, totalPrice,totalAmount_khmer;

    TextInputEditText discount_input;
    TextInputEditText khmer_to_dollar;

    Button submit,paynow;

    Context context;


    public Cart_Adater(List<CartTable> cartTableList, TextView subtotal, TextView totalPrice, TextInputEditText discount_input,Button submit,TextInputEditText khmer_to_dollar,TextView totalAmount_khmer,Button paynow, Context context) {
        this.cartTableList = cartTableList;
        this.subtotal = subtotal;
        this.totalPrice = totalPrice;
        this.discount_input = discount_input;
        this.submit = submit;
        this.khmer_to_dollar = khmer_to_dollar;
        this.totalAmount_khmer = totalAmount_khmer;
        this.paynow = paynow ;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_design_cart, parent,false);

        return new ViewCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCart holder, int position) {
        holder.peng.setText(String.valueOf(cartTableList.get(position).getProductName_eng()));
        holder.qty.setText(String.valueOf(cartTableList.get(position).getProductQty()));
        holder.price.setText(String.valueOf(cartTableList.get(position).getProductCost()));





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


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sum =0;
                for(int i=0;  i< cartTableList.size(); i++){

                    sum += (cartTableList.get(i).getProductCost() * cartTableList.get(i).getProductQty());

                }

                double khmer_dollar = Double.parseDouble(khmer_to_dollar.getText().toString());
                double grand_total = khmer_dollar * sum;

                double discount =0;
                if(discount_input.getText().toString().isEmpty()){
                    totalPrice.setText("$ " + numberFormat(String.valueOf(sum)));

                    totalAmount_khmer.setText("(Real) "+ numberFormat(String.valueOf(grand_total)));

                }else{
                    discount = Double.parseDouble(discount_input.getText().toString());
                    double p = discount /100;
                    double discountAmount = sum - (sum * p);
                    Toast.makeText(submit.getContext(), "discountAmount = " + discountAmount, Toast.LENGTH_SHORT).show();
                    totalPrice.setText("$ " + numberFormat(String.valueOf(discountAmount)));

                    grand_total = khmer_dollar * discountAmount;



                    totalAmount_khmer.setText("(Real) "+ numberFormat(String.valueOf(grand_total)));
                }


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

}
