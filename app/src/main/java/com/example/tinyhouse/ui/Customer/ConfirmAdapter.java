package com.example.tinyhouse.ui.Customer;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.ui.Review.ReviewFragment;

import java.util.List;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientHire> hireList;

    public ConfirmAdapter(List<ClientHire> cleaner, SQLiteDatabase _db) {

        hireList = cleaner;
        db = _db;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cleanerItems = inflater.inflate(R.layout.confirm_cleaner_message, parent, false);
        ConfirmAdapter.ViewHolder holder = new ConfirmAdapter.ViewHolder(cleanerItems);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClientHire hire = hireList.get(position);
        holder.txtCleanerName.setText(hire.getCleanerUsername());
        holder.txtCleanerEmail.setText(hire.getCleanerEmail());
        holder.txvCMessage.setText(hire.getCleanerUsername()+" Confirmed to work on the scheduled day");
        holder.txtAddress.setText(hire.getAddress());
        holder.txtDate.setText(hire.getDate());


        holder.btnRCleanerReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewFragment fragment =new ReviewFragment();
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
        TextView txtCleanerName, txtCleanerEmail, txtDate, txvCMessage, txtAddress;
        Button btnRCleanerReview;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtCleanerName = itemView.findViewById(R.id.txtCleanerName);
            txtCleanerEmail = itemView.findViewById(R.id.txtCleanerEmail);
            txvCMessage = itemView.findViewById(R.id.txvCMessage);
            btnRCleanerReview = itemView.findViewById(R.id.btnRCleanerReview);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtAddress = itemView.findViewById(R.id.txtAddress);

        }
    }
}
