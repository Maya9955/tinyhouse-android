package com.example.tinyhouse.ui.Review;

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
import com.example.tinyhouse.ui.Cleaner.JobAdapter;
import com.example.tinyhouse.ui.Customer.ClientHire;
import com.example.tinyhouse.ui.Customer.PostAdapter;

import java.util.List;

public class ViewReviewFragment extends Fragment {

    private ViewReviewViewModel mViewModel;
    SQLiteDatabase db;

    public static ViewReviewFragment newInstance() {
        return new ViewReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_review, container, false);
        setDB();
        //set recycle view
        RecyclerView recyclerView = view.findViewById(R.id.rcvAllReview);
        Review review = new Review();
        List<Review> reviewList=review.GetReview(db);
        ReviewAdapter adapter= new ReviewAdapter(reviewList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewReviewViewModel.class);
        // TODO: Use the ViewModel
    }

    //setting DB
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Reviews(id integer primary key Autoincrement,UserName text,EmailID text,Review text,UserNamefor text)");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}