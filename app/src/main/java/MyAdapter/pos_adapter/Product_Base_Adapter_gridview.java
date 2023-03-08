package MyAdapter.pos_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.Customer;
import Model.ProductTable;
import Mydatabase.BlueTeachnology_Database;

public class Product_Base_Adapter_gridview extends BaseAdapter implements Filterable {

    List<ProductTable> productTableList;
    List<ProductTable> productTableListFull;
    Context context;

    public Product_Base_Adapter_gridview(List<ProductTable> productTableList, Context context) {
        this.productTableList = productTableList;
        this.context = context;
        this.productTableListFull = new ArrayList<>(productTableList);
    }

    @Override
    public int getCount() {
        return productTableList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.single_gridview_pos,viewGroup,false);

        }

        ImageView product_img;
        TextView english, price, khmer;
        ImageButton cart;

        product_img = view.findViewById(R.id.category_with_product_image);
        english = view.findViewById(R.id.category_with_product_name_english);
        price= view.findViewById(R.id.category_with_product_price);
        khmer = view.findViewById(R.id.category_with_product_name_khmer);



        english.setText(String.valueOf(productTableList.get(i).getProductName_eng()));
        price.setText(String.valueOf(productTableList.get(i).getProduct_cost()));
        khmer.setText(String.valueOf(productTableList.get(i).getProductName_kh()));
        cart = view.findViewById(R.id.category_with_product_button_cart);


        Glide.with(context).load(productTableList.get(i).getImage_product()).into(product_img);



        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartTable cartTable=new CartTable();

                cartTable.setProductQty(1);
                cartTable.setProductCost(productTableList.get(i).getProduct_cost());
                cartTable.setProduct_img(productTableList.get(i).getImage_product());
                cartTable.setProductName_eng(productTableList.get(i).getProductName_eng());
                cartTable.setProductName_kh(productTableList.get(i).getProductName_kh());



                BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();


                //if name == name  false
                //if name # name false
                if (blueTeachnology_dao.cartExists(cartTable.getProductName_eng(), cartTable.getProductName_kh())){
                    Toast.makeText(context, "You Add to cart already", Toast.LENGTH_SHORT).show();
                }else {
                    blueTeachnology_dao.insertCart(cartTable);
                    Toast.makeText(context, "" + productTableList.get(i).getProductName_eng(), Toast.LENGTH_SHORT).show();

                }

               }
        });






        return view;
    }

    @Override
    public Filter getFilter() {
        return AllProductFilter;
    }
    private Filter AllProductFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ProductTable> filterList =new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filterList.addAll(productTableListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();


                for(ProductTable productTable: productTableListFull){
                    if(productTable.getProductName_eng().toLowerCase().contains(filterPattern) || productTable.getProductName_kh().toLowerCase().contains(filterPattern) || productTable.getProduct_barCode().toLowerCase().contains(filterPattern)){
                        filterList.add(productTable);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productTableList.clear();
            productTableList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
