package com.example.tinyhouse.ui.Cleaner;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.ui.Customer.ClientHire;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientHire> hireList;
//    String CleanerEmail;


    public JobAdapter(List<ClientHire> hire, SQLiteDatabase _db){
        hireList = hire;
        db = _db;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View eventItems = inflater.inflate(R.layout.job_item,parent,false);
        ViewHolder holder = new ViewHolder(eventItems);
//        SharedPreference preference= new SharedPreference();
//        String email = preference.GetString(parent.getContext()),SharedPreference.USER_EMAIL);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.ViewHolder holder, int position) {
       ClientHire hire = hireList.get(position);
        holder.txtPostMAddress.setText(hire.getAddress());
        holder.txtPostMDate.setText(hire.getDate());
//        holder.txtID.setText(hire.getID());
//        CleanerEmail=hire.getCleanerEmail();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hire.getImage(),0,hire.getImage().length);
        holder.imgPostMHouse.setImageBitmap(bitmap);

//        holder.btnApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                hire.UpdateCleanerEmail(db,CleanerEmail,email);
//                ((MainActivity)view.getContext()).hire = hire;
//                ApplyJobFragment fragment =new ApplyJobFragment();
//                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.nav_host_fragment_content_main,fragment);
//                ft.addToBackStack(null);
//                ft.commit();
//
//
//            }
//        });

        holder.btnJobDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)v.getContext()).hire = hire;
                ViewDetailJobsFragment fragment =new ViewDetailJobsFragment();
                FragmentTransaction ft=((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

//        holder.btnPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String FloorType = holder.txtFloorType.getText().toString();
//
//                if (FloorType.equalsIgnoreCase("Floor Type - Carpet")){
//                    holder.txtPayment.setText(String.valueOf("PAYMENT AMOUNT IS $"+400));
//
//                }else if(FloorType.equalsIgnoreCase("Floor Type - Tile")) {
//                    holder.txtPayment.setText(String.valueOf("PAYMENT AMOUNT IS $"+200));
//
//                }else if(FloorType.equalsIgnoreCase("Floor Type - Concrete")) {
//                    holder.txtPayment.setText(String.valueOf("PAYMENT AMOUNT IS $"+150));
//
//                }else if(FloorType.equalsIgnoreCase("Floor Type - Wood")) {
//                    holder.txtPayment.setText(String.valueOf("PAYMENT AMOUNT IS $"+300));
//
//                }else if(FloorType.equalsIgnoreCase("Floor Type") ){
//                    Toast.makeText(v.getContext(), "Payment is Not Available", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return hireList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPostMAddress,txtPostMDate,txtID;
        ImageView imgPostMHouse;
        Button btnJobDetails;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtPostMAddress = itemView.findViewById(R.id.txtPostAddress);
            txtPostMDate=itemView.findViewById(R.id.txtPostDate);
            btnJobDetails=itemView.findViewById(R.id.btnJobDetails);
//            txtID=itemView.findViewById(R.id.txtID);
            imgPostMHouse=itemView.findViewById(R.id.imgPostMHouse);




        }
    }
}
