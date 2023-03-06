package com.example.bluesystemwithroomdatabase.Expanse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.Customer.ViewCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityViewAllExpanseBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.ExpanseTable;
import Model.Invoice;
import MyAdapter.Expense_Adapter.Expense_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class ViewAllExpanse extends AppCompatActivity {


    ActivityViewAllExpanseBinding binding;
    List<Invoice> invoiceList;
    BlueTeachnology_Dao blueTeachnology_dao;
    List<ExpanseTable> expanseTableList;
    Expense_Adapter expense_adapter ;
    ArrayList<PieEntry> pieEntries;

    double amount_dollar;
    long amount_khmer = 0;
    double invoice = 0;
    double expense = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllExpanseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupPie();
        pieEntries = new ArrayList<>();


        binding.addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showExpense.setVisibility(view.GONE);
                binding.showAddExpense.setVisibility(view.VISIBLE);
            }
        });
        binding.backExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.showExpense.setVisibility(view.VISIBLE);
                binding.showAddExpense.setVisibility(view.GONE);
            }
        });

        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        invoiceList = blueTeachnology_dao.getAllInvoice();



        //show data from expense
        expanseTableList = blueTeachnology_dao.getAllExpense();
        expense_adapter = new Expense_Adapter(expanseTableList, getApplicationContext());
        binding.listExpense.setLayoutManager(new LinearLayoutManager(this));
        binding.listExpense.setAdapter(expense_adapter);


        binding.saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExpense saveExpense = new saveExpense();
                saveExpense.execute();
            }
        });

        //income
        for(int i=0; i< invoiceList.size() ; i++){
            invoice += invoiceList.get(i).getGrand_total_dollar();




        }
        loadPie();
        pieEntries.add(new PieEntry((int) invoice, "Incone"));
        //expense
        for (int i=0;i < expanseTableList.size(); i++){
            expense += expanseTableList.get(i).getExpense_mony();


        }
        loadPie();
        pieEntries.add(new PieEntry((int) expense, "Expense"));






        amount_dollar = invoice - expense;
        pieEntries.add(new PieEntry((int) amount_dollar, "Amount balance"));
        loadPie();
        binding.income.setText(String.valueOf(invoice));
        binding.expense.setText(String.valueOf(expense));

        binding.amountGrandDollar.setText(String.valueOf(amount_dollar));




        //custom
        if(amount_dollar < 0){
            binding.amountGrandDollar.setTextColor(Color.RED);
        }else{
            binding.amountGrandDollar.setTextColor(Color.GREEN);
        }


    }

    public void setupPie(){
        binding.pieExpense.setDrawHoleEnabled(true);
        binding.pieExpense.setUsePercentValues(true);
        binding.pieExpense.setEntryLabelTextSize(15);
        binding.pieExpense.setCenterText("Expense");
        binding.pieExpense.setCenterTextSize(20);
        binding.pieExpense.getDescription().setEnabled(false);


        Legend legend =  binding.pieExpense.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

    }

    public void loadPie(){
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);

        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Expense");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter( binding.pieExpense));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);



        binding.pieExpense.setData(data);
        binding.pieExpense.invalidate();

        binding.pieExpense.animateY(1400, Easing.EaseInOutBounce);
    }




    class saveExpense extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            System.out.println(cal.getTime());

            ExpanseTable expanseTable = new ExpanseTable();

            expanseTable.setExpanse_description(binding.expenseDescription.getText().toString());
            expanseTable.setExpense_mony(Double.parseDouble(binding.expenseMoney.getText().toString()));
            expanseTable.setCreate_date(String.valueOf(cal.getTime()));

            blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
            blueTeachnology_dao.insertExpense(expanseTable);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            recreate();
            Toast.makeText(ViewAllExpanse.this, "Save record", Toast.LENGTH_SHORT).show();
        }
    }

}