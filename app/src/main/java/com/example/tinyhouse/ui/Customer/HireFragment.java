package com.example.tinyhouse.ui.Customer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HireFragment extends Fragment {

    private HireViewModel mViewModel;
    TextView txvID, txtPrice;
    EditText edtName,edtAddress,edtCNo, edtBedroom,edtBathroom,edtDate,edtLocation;
    ImageView imgHouse;
    Spinner spFloorType;
    Button btnHire,btnEditDetails,btnView,btnPirce;
    Calendar calendar = Calendar.getInstance();
    Bitmap pic;
    SQLiteDatabase db;


    
    public static HireFragment newInstance() {
        return new HireFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hire, container, false);

        String [] values =
                {"Floor Type","Carpet","Tile","Concrete","Wood"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spFloorType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        setDB();

        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtCNo = view.findViewById(R.id.edtCNo);
        spFloorType = view.findViewById(R.id.spFloorType);
        edtBedroom = view.findViewById(R.id.edtBedroom);
        edtBathroom = view.findViewById(R.id.edtBathroom);
        edtDate = view.findViewById(R.id.edtDate);
        edtLocation = view.findViewById(R.id.edtLocation);
        imgHouse = view.findViewById(R.id.imgHouse);
        btnHire = view.findViewById(R.id.btnHire);
        btnEditDetails = view.findViewById(R.id.btnEditDetails);
        btnView = view.findViewById(R.id.btnView);
        txvID= view.findViewById(R.id.txvID);
        txtPrice= view.findViewById(R.id.txtPrice);
        btnPirce= view.findViewById(R.id.btnPrice);

        //        return data to the event fragment when the edit button is clicked

        if(((MainActivity)getActivity()).hire!=null){
            ClientHire h=((MainActivity)getActivity()).hire;
            edtName.setText(h.getName());
            edtAddress.setText(h.getAddress());
            edtCNo.setText(h.getContactNo());
            edtBedroom.setText(h.getBedroom());
            edtBathroom.setText(h.getBathroom());
            edtDate.setText(h.getDate());
            edtLocation.setText(h.getLocation());
            byte[] imgArray =h.getImage();
            pic = BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
            imgHouse.setImageBitmap(pic);
            txvID.setText(String.valueOf(h.getID()));
        }


//      when click edtDate open calender
        DatePickerDialog.OnDateSetListener listner=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String date = "dd/mm/yyyy";
                SimpleDateFormat format =new SimpleDateFormat(date, Locale.US);
                edtDate.setText(format.format(calendar.getTime()));
            }
        };

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(view.getContext(),listner,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


//      when imgHouse click add image by camera and gallery
        ActivityResultLauncher camLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                pic =(Bitmap) intent.getExtras().get("data");
                imgHouse.setImageBitmap(pic);
//                imageView.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
            }
        });


        ActivityResultLauncher galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                Uri selectedimage = intent.getData();

//                Alternative method **************************************************************************
//                try {
//                    pic = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selectedimage);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                imgHouse.setImageURI(selectedimage);
                pic = ((BitmapDrawable)imgHouse.getDrawable()).getBitmap();
            }
        });

        imgHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(imgHouse.getContext());
                builder.setMessage("Please select an option").setTitle("Image Selection").setPositiveButton("Use the camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        camLauncher.launch(intent);
                    }
                }).setNegativeButton("Select Form Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryLauncher.launch(intent);
                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });

//      when click edtLocation open google map
        ActivityResultLauncher<Intent>launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK)
                {
                    Intent intent = result.getData();
                    LatLng LatLng= intent.getParcelableExtra("Location");
                    edtLocation.setText(String.valueOf(LatLng));
                }

            }


        });

        edtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), MainActivity.MapsActivity.class);
                launcher.launch(intent);
            }
        });
        btnPirce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FloorType = spFloorType.getSelectedItem().toString();

                if (FloorType.equalsIgnoreCase("Carpet")){
                    txtPrice.setText(String.valueOf("$"+400));
                    Toast.makeText(view.getContext(), "Price Automatically Calculated", Toast.LENGTH_LONG).show();
                }else if(FloorType.equalsIgnoreCase("Tile")) {
                    txtPrice.setText(String.valueOf("$"+200));
                    Toast.makeText(view.getContext(), "Price Automatically Calculated", Toast.LENGTH_LONG).show();
                }else if(FloorType.equalsIgnoreCase("Concrete")) {
                    txtPrice.setText(String.valueOf("$"+150));
                    Toast.makeText(view.getContext(), "Price Automatically Calculated", Toast.LENGTH_LONG).show();
                }else if(FloorType.equalsIgnoreCase("Wood")) {
                    txtPrice.setText(String.valueOf("$"+300));
                    Toast.makeText(view.getContext(), "Price Automatically Calculated", Toast.LENGTH_LONG).show();
                }else if(FloorType.equalsIgnoreCase("Floor Type") ){
                    Toast.makeText(view.getContext(), "Select Floor Type", Toast.LENGTH_LONG).show();
                }

            }
        });



        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClientHire hire = new ClientHire();
                    hire.setName(edtName.getText().toString());
                    hire.setAddress(edtAddress.getText().toString());
                    hire.setContactNo(edtCNo.getText().toString());
                    hire.setFloorType(spFloorType.getSelectedItem().toString());
                    hire.setBedroom(edtBedroom.getText().toString());
                    hire.setBathroom(edtBathroom.getText().toString());
                    hire.setDate(edtDate.getText().toString());
                    hire.setLocation(edtLocation.getText().toString());
//                    hire.setCleanerEmail("");
                    SharedPreference preference= new SharedPreference();
                    String mail = preference.GetString(getContext(),SharedPreference.USER_EMAIL);
                    hire.setEmail(mail);



                    // convert image to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] imgArray = stream.toByteArray();
                    hire.setImage(imgArray);





                    hire.save(db);
//                    btnHire.setVisibility(View.INVISIBLE);
                    Toast.makeText(view.getContext(), "Successfully Details Posted", Toast.LENGTH_LONG).show();
                }
                catch(Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(),"Error in Posting Details",Toast.LENGTH_LONG).show();
                    Log.e("",ex.getMessage());
                }
            }

        });

        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClientHire hire = new ClientHire();
                    hire.setName(edtName.getText().toString());
                    hire.setAddress(edtAddress.getText().toString());
                    hire.setContactNo(edtCNo.getText().toString());
                    hire.setFloorType(spFloorType.getSelectedItem().toString());
                    hire.setBedroom(edtBedroom.getText().toString());
                    hire.setBathroom(edtBathroom.getText().toString());
                    hire.setDate(edtDate.getText().toString());
                    hire.setLocation(edtLocation.getText().toString());




                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] imgArray = stream.toByteArray();
                    hire.setImage(imgArray);

                    hire.setID(Integer.valueOf(txvID.getText().toString()));

                    hire.Update(db);
                    Toast.makeText(view.getContext(), "Successfully Details Posted", Toast.LENGTH_LONG).show();
                }
                catch(Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(),"Error in Editing Post",Toast.LENGTH_LONG).show();
                    Log.e("",ex.getMessage());
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPostFragment fragment = new ViewPostFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
            }
        });






        return view;


    }



//    private void populateSpinnerFloorType() {
//        ArrayAdapter<String>floorTypeAdapter= ArrayAdapter.createFromResource(this, R.array.floortype, android.R.layout.simple_spinner_item);
//        floorTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(floorTypeAdapter);
//        spinner.setOnItemSelectedListener(this);
//
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HireViewModel.class);
        // TODO: Use the ViewModel
    }
    //setting DB
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Post(id integer primary key Autoincrement,Name text,Address text,ContactNo text," +
                    "FloorType text,Bedroom text,Bathroom text,Date text," +
                    "Location text,Image blob,Email text, CleanerEmail text, CleanerUsername text)");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
