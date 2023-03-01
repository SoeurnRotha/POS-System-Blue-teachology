package MyAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.Products.Update_products;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Customer;
import Model.ProductTable;
import Mydatabase.BlueTeachnology_Database;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ProductViewHolder> {

    List<ProductTable> productTableList;

    public Product_Adapter(List<ProductTable> productTableList) {
        this.productTableList = productTableList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_designer_view_products,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final ProductTable productTable = productTableList.get(position);

        if(productTable == null){
            return;
        }



        holder.pname_eng.setText(String.valueOf(productTableList.get(position).getProductName_eng()));

        holder.pname_kh.setText(String.valueOf(productTableList.get(position).getProductName_kh()));

        holder.pqty.setText(String.valueOf(productTableList.get(position).getProduct_qty()));

        holder.pprice.setText(String.valueOf(productTableList.get(position).getProduct_Price()));

        holder.pcost.setText(String.valueOf(productTableList.get(position).getProduct_cost()));

        holder.ptax.setText(String.valueOf(productTableList.get(position).getTax()));

        holder.pdate.setText(String.valueOf(productTableList.get(position).getProduct_date()));

        holder.pbarcode.setText(String.valueOf(productTableList.get(position).getProduct_barCode()));


        //edit product
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Update_products.class);
                intent.putExtra("products",productTable);

                view.getContext().startActivity(intent);

            }
        });


        //delete products
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                  builder.setTitle("DELETE");
                  builder.setMessage("Do you want to Delete Category");
                  builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int index) {
                          Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                          Toast.makeText(builder.getContext(), "Yes", Toast.LENGTH_SHORT).show();

                          BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();


                          blueTeachnology_dao.deleteProductByid(productTableList.get(position).getProductId());


                          productTableList.remove(position);
                          notifyDataSetChanged();

                      }
                  });

                  builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int index) {
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
        return productTableList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView  pname_eng, pname_kh,pqty,pprice,ptax, pcost,pdate,pbarcode;

        ImageButton edit,delete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            pname_eng = itemView.findViewById(R.id.show_product_name_eng);
            pname_kh = itemView.findViewById(R.id.show_product_name_kh);
            pqty = itemView.findViewById(R.id.show_product_qty);
            pprice = itemView.findViewById(R.id.show_product_price);

            pcost = itemView.findViewById(R.id.show_product_cost);
            ptax = itemView.findViewById(R.id.show_product_tax);
            pdate = itemView.findViewById(R.id.show_product_date);
            pbarcode = itemView.findViewById(R.id.show_product_barcode);

            //button
            edit = itemView.findViewById(R.id.button_edit_product);
            delete = itemView.findViewById(R.id.button_delete_product);


        }
    }
}
