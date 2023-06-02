package com.example.tinyhouse.ui.BackLoad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tinyhouse.R;

public class Home3Fragment extends Fragment {

    private Home3ViewModel mViewModel;
    ImageButton btnPrevious1;

    public static Home3Fragment newInstance() {
        return new Home3Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home3, container, false);
        btnPrevious1 = view.findViewById(R.id.btnPrevious1);


        btnPrevious1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home2Fragment2 fragment = new Home2Fragment2();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
            }
        });



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Home3ViewModel.class);
        // TODO: Use the ViewModel
    }

}