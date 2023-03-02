package MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.PaymentMethod;

public class Payment_method_bastAdapter  extends BaseAdapter {

    Context context;
    List<PaymentMethod> paymentMethodList;

    public Payment_method_bastAdapter(Context context, List<PaymentMethod> paymentMethodList) {
        this.context = context;
        this.paymentMethodList = paymentMethodList;
    }

    @Override
    public int getCount() {
        return paymentMethodList.size();
    }

    @Override
    public Object getItem(int i) {
        return paymentMethodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      if(view == null){
          view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_for_items,null,false);
          PaymentMethod paymentMethod = paymentMethodList.get(i);
          TextView payment_type = view.findViewById(R.id.show_items);

          payment_type.setText(paymentMethod.getDecription());

      }

        return view;
    }
}
