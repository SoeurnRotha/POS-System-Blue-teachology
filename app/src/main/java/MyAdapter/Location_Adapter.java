package MyAdapter;

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

import com.example.bluesystemwithroomdatabase.Inventory.UpdateLocation;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.LocationTable;
import Mydatabase.BlueTeachnology_Database;

public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.LocationViewHolder> {

    List<LocationTable> locationTableList;

    public Location_Adapter(List<LocationTable> locationTableList) {
        this.locationTableList = locationTableList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        final LocationTable locationTable = locationTableList.get(position);
        if(locationTable == null){
            return;
        }

        holder.english.setText(String.valueOf(locationTableList.get(position).getLocationName_eng()));

        holder.khmer.setText(String.valueOf(locationTableList.get(position).getLocationName_kh()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateLocation.class);
                intent.putExtra("location", locationTable);
                view.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Delete Location");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        Toast.makeText(builder.getContext(), "Yes", Toast.LENGTH_SHORT).show();


                        //delete


                        BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deleteLocationByid(locationTableList.get(position).getLocation_Id());

                        locationTableList.remove(position);

                        notifyDataSetChanged();

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

    }

    @Override
    public int getItemCount() {
        return locationTableList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{

        ImageButton delete;
        TextView english,khmer;
        CardView edit;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            english = itemView.findViewById(R.id.location_english_show);
            khmer = itemView.findViewById(R.id.location_khmer_show);


            delete = itemView.findViewById(R.id.delete_location);
            edit = itemView.findViewById(R.id.card_edit_location);
        }
    }
}
