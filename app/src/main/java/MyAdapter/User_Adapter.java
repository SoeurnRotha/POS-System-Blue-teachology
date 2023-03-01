package MyAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.User.Update_User;

import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.UserTable;
import Mydatabase.BlueTeachnology_Database;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.UserViewHolder> {
    List<UserTable> userTableList;




    public User_Adapter(List<UserTable> userTableList) {
        this.userTableList = userTableList;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_design,parent ,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.id.setText(String.valueOf(userTableList.get(position).getUserId()));
        holder.username.setText(String.valueOf(userTableList.get(position).getUsername()));
        holder.password.setText(String.valueOf(userTableList.get(position).getPassword()));


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
                Intent intent = new Intent(new Intent(holder.edit.getContext(), Update_User.class));
                intent.putExtra("uid",String.valueOf(userTableList.get(position).getUserId()));
                intent.putExtra("uname",String.valueOf(userTableList.get(position).getUsername()));
                intent.putExtra("upassword", String.valueOf(userTableList.get(position).getPassword()));
                holder.edit.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return userTableList.size();
    }

    public class UserViewHolder extends  RecyclerView.ViewHolder{

        TextView id,username,password;
        Button edit , delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.user_id);
            username = itemView.findViewById(R.id.user_name);
            password = itemView.findViewById(R.id.user_password);


            edit = itemView.findViewById(R.id.user_edit);
            delete = itemView.findViewById(R.id.user_delete);

        }
    }
}
