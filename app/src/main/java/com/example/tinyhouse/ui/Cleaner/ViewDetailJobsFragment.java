package com.example.tinyhouse.ui.Cleaner;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;
import com.example.tinyhouse.ui.Customer.ClientHire;
import com.example.tinyhouse.ui.Review.ReviewFragment;

public class ViewDetailJobsFragment extends Fragment {

    private ViewDetailJobsViewModel mViewModel;
    TextView txtID,txtEmail,txtName,txtPostName,txtPostAddress,txtPostCNo,txtPostFloorType,txtPostBedroom,txtPostBathroom,txtPostDate;
    ImageView imgPostMHouse;
    Button btnApply,btnReview;
    SQLiteDatabase db;
    Bitmap pic;
    String jobID;


    public static ViewDetailJobsFragment newInstance() {
        return new ViewDetailJobsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_detail_jobs, container, false);

        setDB();

        txtPostName = view.findViewById(R.id.txtPostName);
        txtPostAddress = view.findViewById(R.id.txtPostAddress);
        txtPostCNo = view.findViewById(R.id.txtPostCNo);
        txtPostFloorType = view.findViewById(R.id.txtPostFloorType);
        txtPostBedroom = view.findViewById(R.id.txtPostBedroom);
        txtPostBathroom = view.findViewById(R.id.txtPostBathroom);
        txtPostDate = view.findViewById(R.id.txtPostDate);
        imgPostMHouse = view.findViewById(R.id.imgPostMHouse);
        btnApply = view.findViewById(R.id.btnApply);
        btnReview = view.findViewById(R.id.btnReview);
        txtID = view.findViewById(R.id.txtID);
       txtEmail = view.findViewById(R.id.txtEmail);
        txtName = view.findViewById(R.id.txtName);




        if(((MainActivity)getActivity()).hire!=null){
            ClientHire h=((MainActivity)getActivity()).hire;
            txtPostName.setText(h.getName());
            txtPostAddress.setText(h.getAddress());
            txtPostCNo.setText(h.getContactNo());
            txtPostBedroom.setText(h.getBedroom());
            txtPostBathroom.setText(h.getBathroom());
            txtPostDate.setText(h.getDate());
            txtPostFloorType.setText(h.getFloorType());
            txtEmail.setText(h.getCleanerEmail());
            txtName.setText(h.getCleanerUsername());

            byte[] imgArray =h.getImage();
            pic = BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
            imgPostMHouse.setImageBitmap(pic);
            txtID.setText(String.valueOf(h.getID()));
            jobID = txtID.getText().toString();

        }
            SharedPreference preference= new SharedPreference();
            String CM = preference.GetString(view.getContext(),SharedPreference.USER_EMAIL);
            String CName = preference.GetString(view.getContext(),SharedPreference.KEY_USER_NAME);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientHire ch= new ClientHire();
                ch.UpdateCleanerEmail(db,CM, jobID);
                ch.UpdateCleanerUsername(db,CName, jobID);

                Toast.makeText(getActivity().getApplicationContext(),"Applied Message sent to House Owner! ",Toast.LENGTH_LONG).show();



            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ReviewFragment fragment = new ReviewFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();


            }
        });




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewDetailJobsViewModel.class);
        // TODO: Use the ViewModel
    }
    //setting DB
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Post(id integer primary key Autoincrement,Name text,Address text,ContactNo text," +
                    "FloorType text,Bedroom text,Bathroom text,Date text," +
                    "Location text,Image blob,Email text,CleanerEmail text,CleanerUsername text,Review text)");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}