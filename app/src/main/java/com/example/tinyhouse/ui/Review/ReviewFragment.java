package com.example.tinyhouse.ui.Review;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;
import com.example.tinyhouse.ui.Customer.ClientHire;

public class ReviewFragment extends Fragment {

    private ReviewViewModel mViewModel;
    TextView txtRID,txtReviewEmail,txtReviewName;
    EditText edtReview,edtUsernamefor,edtUsernamefrom;
    Button btnPostReview;
    SQLiteDatabase db;
    String jobID;

    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_review, container, false);
        setDB();

        txtReviewName = view.findViewById(R.id.txtReviewName);
        txtReviewEmail = view.findViewById(R.id.txtReviewEmail);
        edtReview = view.findViewById(R.id.edtReview);
        edtUsernamefor = view.findViewById(R.id.edtUsernamefor);
        edtUsernamefrom = view.findViewById(R.id.edtUsernamefrom);
        btnPostReview = view.findViewById(R.id.btnPostReview);
        txtRID = view.findViewById(R.id.txtRID);


        SharedPreference preference= new SharedPreference();
        String CM = preference.GetString(view.getContext(),SharedPreference.USER_EMAIL);
        String CName = preference.GetString(view.getContext(),SharedPreference.KEY_USER_NAME);
        txtReviewName.setText(CName);
        txtReviewEmail.setText(CM);
        Review review= new Review();
        txtRID.setText(String.valueOf(review.getID()));
        jobID = txtRID.getText().toString();




        btnPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Review review= new Review();
                    review.setUserNamefor(edtUsernamefor.getText().toString());
                    review.setUserName(edtUsernamefrom.getText().toString());
                    review.setReview(edtReview.getText().toString());

                    review.save(db);
                    Toast.makeText(view.getContext(), "Successfully Details Posted", Toast.LENGTH_LONG).show();
                }
                catch(Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(),"Error in Posting Details"+ex.getMessage(),Toast.LENGTH_LONG).show();
                    Log.e("",ex.getMessage());
                }
            }

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);
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