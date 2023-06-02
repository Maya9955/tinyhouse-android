package com.example.tinyhouse.ui.Login;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    SQLiteDatabase db;
    EditText edtClName,edtClEmail,edtClPassword;
    Button btnReg;
    Spinner spUserType;


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

//        String [] values =
//                {"User Type","Customer","Cleaner"};
//        Spinner spinner = (Spinner) view.findViewById(R.id.spUserType);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        spinner.setAdapter(adapter);

        String [] values =
                {"User Type","Customer","Cleaner"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spUserType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        edtClName = view.findViewById(R.id.edtClName);
        edtClEmail = view.findViewById(R.id.edtClEmail);
        edtClPassword = view.findViewById(R.id.edtClPassword);
        spUserType=view.findViewById(R.id.spUserType);
        btnReg = view.findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDB();
                User user = new User();
                user.setName(edtClName.getText().toString());
                user.setEmail(edtClEmail.getText().toString());
                user.setPassword(edtClPassword.getText().toString());
                user.setUserType(spUserType.getSelectedItem().toString());
                user.Save(db);
                SharedPreference preference=new SharedPreference();
                preference.SaveString(view.getContext(),user.getName(),SharedPreference.KEY_USER_NAME);

                //Move to login frag
                LoginFragment fragment = new LoginFragment();
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

    //setting db
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists User(id integer primary key Autoincrement,name text,email text unique,password text ,userType text)");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}