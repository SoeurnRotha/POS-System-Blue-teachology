package MyAdapter.UnitAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.Customer.UpdateCustomer;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.Unit.UpdateUnit;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Customer;
import Model.UnitModel;
import Mydatabase.BlueTeachnology_Database;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewUnit>{


    List<UnitModel> unitModelList;
    Context context;

    public UnitAdapter(List<UnitModel> unitModelList, Context context) {
        this.unitModelList = unitModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewUnit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_unit_list, parent, false);
        return new ViewUnit(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewUnit holder, @SuppressLint("RecyclerView") int position) {
        final UnitModel unitModel = unitModelList.get(position);

        if(unitModel == null){
            return;
        }

        holder.unitTitle.setText(String.valueOf(unitModelList.get(position).getUnitName()));

        holder.unitType.setText(String.valueOf(unitModelList.get(position).getUnitCode()));


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), UpdateUnit.class);
                intent.putExtra("unit",unitModel);
                view.getContext().startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Unit products ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();
                        blueTeachnology_dao.deleteUnitByid(unitModelList.get(position).getUnitId());
                        unitModelList.remove(position);
                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    Toast.makeText(builder.getContext(), "No", Toast.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitModelList.size();
    }

    public class ViewUnit extends RecyclerView.ViewHolder {

        ImageButton delete;
        TextView unitTitle, unitType;
        CardView edit;
        public ViewUnit(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.delete_unit);

            unitTitle = itemView.findViewById(R.id.unit_title);

            unitType = itemView.findViewById(R.id.unit_type);
            edit = itemView.findViewById(R.id.card_edit_unit);
        }
    }
}
