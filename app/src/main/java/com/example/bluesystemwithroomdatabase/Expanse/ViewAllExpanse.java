package com.example.bluesystemwithroomdatabase.Expanse;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.ExpanseTable;
import Model.Invoice;
import Mydatabase.BlueTeachnology_Database;

public class ViewAllExpanse extends AppCompatActivity {


    ActivityViewAllExpanseBinding binding;
    List<Invoice> invoiceList;
    BlueTeachnology_Dao blueTeachnology_dao;
    List<ExpanseTable> expanseTableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllExpanseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupPie();
        loadPie();


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
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

    }

    public void loadPie(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        double invoice = 0;

        for(int i=0 ; i < 10; i++){
//            invoice += invoiceList.get(i).getGrand_total_dollar();
            Toast.makeText(this, "number" +i, Toast.LENGTH_SHORT).show();
         }



        pieEntries.add(new PieEntry(100, "Income"));
        pieEntries.add(new PieEntry(100, "Expense"));

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
        data.setValueTextColor(Color.BLACK);

        binding.pieExpense.setData(data);
        binding.pieExpense.invalidate();
        binding.pieExpense.animateY(1400, Easing.EaseInOutBounce);
    }
}