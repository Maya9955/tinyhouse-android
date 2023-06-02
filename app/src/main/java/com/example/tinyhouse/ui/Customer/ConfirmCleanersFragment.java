package com.example.tinyhouse.ui.Customer;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;

import java.util.List;

public class ConfirmCleanersFragment extends Fragment {
    SQLiteDatabase db;

    private ConfirmCleanersViewModel mViewModel;

    public static ConfirmCleanersFragment newInstance() {
        return new ConfirmCleanersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_confirm_cleaners, container, false);
        setDB();
        //set recycle view
        RecyclerView recyclerView = v.findViewById(R.id.rcvReviews);
        ClientHire hire = new ClientHire();
        SharedPreference preference= new SharedPreference();
        String mail = preference.GetString(getContext(),SharedPreference.USER_EMAIL);
//        String m="edf@gmail.com";
        List<ClientHire> hireList=hire.GetMyCleaner(db,mail);
        ConfirmAdapter adapter= new ConfirmAdapter(hireList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfirmCleanersViewModel.class);
        // TODO: Use the ViewModel
    }

    //setting DB
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Post(id integer primary key Autoincrement,Name text,Address text,ContactNo text," +
                    "FloorType text,Bedroom text,Bathroom text,Date text," +
                    "Location text,Image blob,Email text, CleanerEmail text,CleanerUsername text)");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}