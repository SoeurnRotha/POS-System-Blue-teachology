package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.User.Update_User;

import java.util.ArrayList;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.ProductTable;
import Model.UserTable;
import Mydatabase.BlueTeachnology_Database;
import de.hdodenhof.circleimageview.CircleImageView;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.UserViewHolder> implements Filterable {
    List<UserTable> userTableList;
    List<UserTable> userTableListFull;
    Context context;
    BlueTeachnology_Dao blueTeachnology_dao;
    String userRoles;
    String Promisstion;


    public User_Adapter(List<UserTable> userTableList, Context context) {
        this.userTableList = userTableList;
        this.context = context;
        this.userTableListFull = new ArrayList<>(userTableList);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_design,parent ,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UserTable userTable = userTableList.get(position);

        if(userTable == null){
            return;
        }

        holder.username.setText(String.valueOf(userTableList.get(position).getUsername()));
        holder.password.setText(String.valueOf(userTableList.get(position).getPassword()));

        holder.fristname.setText(String.valueOf(userTableList.get(position).getFrist_name()));
        holder.lastname.setText(String.valueOf(userTableList.get(position).getLast_name()));

        holder.gender.setText(String.valueOf(userTableList.get(position).getGender()));

        holder.bod.setText(String.valueOf(userTableList.get(position).getBrith_of_date()));
//        blueTeachnology_dao = BlueTeachnology_Database.getInstance(context).blueTeachnology_dao();
//
//        userTableList = blueTeachnology_dao.getAlluserInfo();

        if(userTableList.get(position).isAdmin()){
            userRoles = "Admin";
        }
        if (userTableList.get(position).isManager()){
            userRoles = "Manager";

        }
        if (userTableList.get(position).isCashier()){
            userRoles = "Cashier";
        }




        holder.user_roles.setText(String.valueOf(userRoles));


        Glide.with(context).load(userTable.getUserImage()).into(holder.imageView);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(holder.edit.getContext(), Update_User.class));
                intent.putExtra("uid",String.valueOf(userTableList.get(position).getUserId()));
                intent.putExtra("uname",String.valueOf(userTableList.get(position).getUsername()));
                intent.putExtra("upassword", String.valueOf(userTableList.get(position).getPassword()));
                holder.edit.getContext().startActivity(intent);

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Delete user");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        Toast.makeText(builder.getContext(), "Yes", Toast.LENGTH_SHORT).show();


                        //delete


                        BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();

                        blueTeachnology_dao.deleteUserByid(userTableList.get(position).getUserId());

                        userTableList.remove(position);

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


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Update_User.class);
                intent.putExtra("user", userTable);
                view.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return userTableList.size();
    }


    public class UserViewHolder extends  RecyclerView.ViewHolder{

        TextView username,password, fristname, lastname,gender,bod,user_roles;
        Button edit , delete;
        CircleImageView imageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.user_name_user);
            password = itemView.findViewById(R.id.password_user);
            fristname= itemView.findViewById(R.id.frist_name_user);
            lastname = itemView.findViewById(R.id.last_name_user);
            gender = itemView.findViewById(R.id.gender_user);
            bod = itemView.findViewById(R.id.bod_user);
            user_roles = itemView.findViewById(R.id.user_roles);

            edit = itemView.findViewById(R.id.user_edit);
            delete = itemView.findViewById(R.id.user_delete);
            imageView = itemView.findViewById(R.id.show_image_user);

        }
    }


    @Override
    public Filter getFilter() {
        return AllProductFilter;
    }
    private Filter AllProductFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<UserTable> filterList =new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filterList.addAll(userTableListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();


                for(UserTable userTable: userTableListFull){
                    if(userTable.getFrist_name().toLowerCase().contains(filterPattern) || userTable.getLast_name().toLowerCase().contains(filterPattern) || userTable.getUsername().toLowerCase().contains(filterPattern)){
                        filterList.add(userTable);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            userTableList.clear();
            userTableList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
