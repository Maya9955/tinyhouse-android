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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinyhouse.MainActivity;
import com.example.tinyhouse.R;
import com.example.tinyhouse.SharedPreference;
import com.example.tinyhouse.ui.BackLoad.BlankFragment;
import com.example.tinyhouse.ui.Customer.ViewPostFragment;
import com.example.tinyhouse.ui.home.HomeFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    EditText edtCLEmail,edtCLPassword;
    TextView txvWelcome;
    Button btnClLogin,btnLogReg;
    SQLiteDatabase db;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        edtCLEmail = view.findViewById(R.id.edtCLEmail);
        edtCLPassword = view.findViewById(R.id.edtCLPassword);
        txvWelcome = view.findViewById(R.id.txvWelcome);
        btnClLogin = view.findViewById(R.id.btnClLogin);
        btnLogReg= view.findViewById(R.id.btnLogReg);

        //Shared pref and set welcome msg
        SharedPreference preference= new SharedPreference();
        String name = preference.GetString(view.getContext(),SharedPreference.KEY_USER_NAME);
        if (name!=null){
            txvWelcome.setText("Welcome "+name);
        }

        btnClLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDB();
                User user = new User();
                user.setEmail(edtCLEmail.getText().toString());
                user.setPassword(edtCLPassword.getText().toString());
                if(user.CheckLogin(db))

                {
                    String Type = user.getType(db);
                    preference.SaveString(view.getContext(),Type,SharedPreference.USER_TYPE);
                    preference.SaveString(view.getContext(),edtCLEmail.getText().toString(),SharedPreference.USER_EMAIL);


                    preference.SaveBool(view.getContext(),true,SharedPreference.KEY_STATUS);
                    FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                    BlankFragment fragment = new BlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                    trans.addToBackStack(null);
                    trans.commit();

//                   ((MainActivity)view.getContext()).EnableMenu(true);

                }else{

                    Toast.makeText(getActivity().getApplicationContext(),"Wrong email or password!", Toast.LENGTH_LONG).show();

                }


            }
        });

        btnLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment fragment = new RegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    //setting db
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("HireCleanersDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists User(id integer primary key Autoincrement,name text,email text unique,password text,userType text )");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}