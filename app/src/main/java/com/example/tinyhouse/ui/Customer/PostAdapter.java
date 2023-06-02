package com.example.tinyhouse.ui.Customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.PostID;
import com.example.tinyhouse.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientHire> hireList;
    PostID txtId;




    public PostAdapter(List<ClientHire> hire, SQLiteDatabase _db) {

        hireList = hire;
        db = _db;


    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View eventItems = inflater.inflate(R.layout.post_item, parent, false);
        PostAdapter.ViewHolder holder = new PostAdapter.ViewHolder(eventItems);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        ClientHire hire = hireList.get(position);
        holder.txtPostName.setText(hire.getName());
        holder.txtPostAddress.setText(hire.getAddress());
        holder.txtPostCNo.setText("+ (94) "+hire.getContactNo());
        holder.txtPostFloorType.setText("Floor Type - "+hire.getFloorType());
        holder.txtPostBedroom.setText(hire.getBedroom()+" Bedroom");
        holder.txtPostBathroom.setText(hire.getBathroom()+" Bathroom");
//       holder.Price.setText(String.valueof(hire,getPrice()));

        Bitmap bitmap = BitmapFactory.decodeByteArray(hire.getImage(), 0, hire.getImage().length);
        holder.imgPostHouse.setImageBitmap(bitmap);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(holder.btnDelete.getContext());
                builder.setMessage("Are you sure,you want to delete").setTitle("Confirm Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hire.Delete(db);
                        hireList.remove(position);
                        notifyItemRemoved(position);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if no action
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });



        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).hire = hire;
                HireFragment fragment =new HireFragment();
                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();




            }
        });


    }

    @Override
    public int getItemCount() {
        return hireList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPostName, txtPostAddress, txtPostCNo, txtPostFloorType, txtPostBathroom, txtPostBedroom;
        ImageView imgPostHouse;
        ImageButton btnDelete, btnEdit;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtPostName = itemView.findViewById(R.id.txtPostName);
            txtPostAddress = itemView.findViewById(R.id.txtPostAddress);
            txtPostCNo = itemView.findViewById(R.id.txtPostCNo);
            txtPostFloorType = itemView.findViewById(R.id.txtPostFloorType);
            txtPostBathroom = itemView.findViewById(R.id.txtPostBathroom);
            txtPostBedroom = itemView.findViewById(R.id.txtPostBedroom);
            imgPostHouse = itemView.findViewById(R.id.imgPostMHouse);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);




        }
    }
}

