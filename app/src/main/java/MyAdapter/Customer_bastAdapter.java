package MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.Customer;

public class Customer_bastAdapter extends BaseAdapter {

    Context context;
    List<Customer> customers;

    public Customer_bastAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int i) {
        return customers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_for_items,null,false);
            Customer customer = customers.get(i);
            TextView customerName = view.findViewById(R.id.show_items);
            customerName.setText(customer.getCustomerName());
        }

        return view;
    }
}
