package MyAdapter.Expense_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.ExpanseTable;

public class Expense_Adapter extends RecyclerView.Adapter<Expense_Adapter.ViewExpense>{


    List<ExpanseTable> expanseTableList;
    Context context;

    public Expense_Adapter(List<ExpanseTable> expanseTableList, Context context) {
        this.expanseTableList = expanseTableList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewExpense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_expense, parent, false);
        return new ViewExpense(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExpense holder, int position) {


        holder.descriton.setText(String.valueOf(expanseTableList.get(position).getExpanse_description()));
        holder.date.setText(String.valueOf(expanseTableList.get(position).getCreate_date()));
        holder.money.setText(String.valueOf(expanseTableList.get(position).getExpense_mony()));
    }

    @Override
    public int getItemCount() {
        return expanseTableList.size();
    }

    public class ViewExpense extends RecyclerView.ViewHolder {

        TextView date, descriton , money;
        public ViewExpense(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.expense_date);
            descriton = itemView.findViewById(R.id.expense_description_list);
            money = itemView.findViewById(R.id.expense_money_list);
        }
    }
}
