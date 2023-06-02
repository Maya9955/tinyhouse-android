package com.example.tinyhouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tinyhouse.databinding.ActivityMapsBinding;
import com.example.tinyhouse.ui.BackLoad.BlankFragment;
import com.example.tinyhouse.ui.Customer.ClientHire;
import com.example.tinyhouse.ui.Cleaner.ViewJobsFragment;
import com.example.tinyhouse.ui.Customer.ConfirmCleanersFragment;
import com.example.tinyhouse.ui.Customer.HireFragment;
import com.example.tinyhouse.ui.Login.LoginFragment;
import com.example.tinyhouse.ui.Login.RegisterFragment;
import com.example.tinyhouse.ui.Review.ViewReviewFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;




import com.example.tinyhouse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ClientHire hire;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    boolean register = false;
    boolean status = false;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_hire, R.id.nav_jobs, R.id.nav_confirm,R.id.nav_Review)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        // navigation manually
        getSupportFragmentManager().popBackStack();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//
        //shared preference part
        SharedPreference preference = new SharedPreference();
        status = preference.GetBoolean(getApplicationContext(), SharedPreference.KEY_STATUS);
        String name = preference.GetString(getApplicationContext(), SharedPreference.KEY_USER_NAME);

        if (name != null) {
            register = true;
        }

        //check register
        if (register) {
//
//
//                //check login
            if (status) {
                BlankFragment fragment = new BlankFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();

                String UT = preference.GetString(getApplicationContext(),SharedPreference.USER_TYPE);
                if (UT.equalsIgnoreCase("Customer")){
                    //customer
//
                    Menu menu = navigationView.getMenu();
                    MenuItem item = menu.findItem(R.id.nav_jobs);
                    item.setVisible(false);

                }
                else if (UT.equalsIgnoreCase("Cleaner")){
                    //cleaner
                    Menu menu = navigationView.getMenu();
                    MenuItem item = menu.findItem(R.id.nav_hire);
                    item.setVisible(false);
                    MenuItem ite = menu.findItem(R.id.nav_confirm);
                    ite.setVisible(false);


                }

            } else {
                LoginFragment fragment = new LoginFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
                Menu menu = navigationView.getMenu();
                MenuItem item = menu.findItem(R.id.nav_hire);
                item.setVisible(false);
                item = menu.findItem(R.id.nav_jobs);
                item.setVisible(false);
                item = menu.findItem(R.id.nav_logout);
                item.setVisible(false);
                item = menu.findItem(R.id.nav_confirm);
                item.setVisible(false);
                item = menu.findItem(R.id.nav_Review);
                item.setVisible(false);
//                item = menu.findItem(R.id.nav_home);
//                item.setVisible(false);

//                    EnableMenu(false);
            }
        } else {
            RegisterFragment fragment = new RegisterFragment();
            trans.replace(R.id.nav_host_fragment_content_main, fragment);
            trans.addToBackStack(null);
            trans.commit();
            Menu menu = navigationView.getMenu();
            MenuItem item = menu.findItem(R.id.nav_hire);
            item.setVisible(false);
            item = menu.findItem(R.id.nav_jobs);
            item.setVisible(false);
            item = menu.findItem(R.id.nav_logout);
            item.setVisible(false);
            item = menu.findItem(R.id.nav_confirm);
            item.setVisible(false);
            item = menu.findItem(R.id.nav_Review);
            item.setVisible(false);
//            item = menu.findItem(R.id.nav_home);
//            item.setVisible(false);

//                EnableMenu(false);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuID = item.getItemId();
                getSupportFragmentManager().popBackStack();
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                if (menuID == R.id.nav_home) {
                    BlankFragment fragment = new BlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                } else if (menuID == R.id.nav_hire) {
                    HireFragment fragment = new HireFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                } else if (menuID == R.id.nav_jobs) {
                    ViewJobsFragment fragment = new ViewJobsFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                } else if (menuID == R.id.nav_confirm) {
                    ConfirmCleanersFragment fragment = new ConfirmCleanersFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                } else if (menuID == R.id.nav_Review) {
                    ViewReviewFragment fragment = new ViewReviewFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                } else if (menuID == R.id.nav_logout) {
                    LoginFragment fragment = new LoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                    trans.addToBackStack(null);
                    preference.SaveBool(getApplicationContext(), false, SharedPreference.KEY_STATUS);
//                    EnableMenu(false);

                } else if (menuID == R.id.nav_exit) {
                    finish();
                }
                trans.addToBackStack(null);
                trans.commit();
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
//
//        // Method to disable a nav menu item
////        Menu menu = navigationView.getMenu();
////        MenuItem item=menu.findItem(R.id.nav_event);
////        item.setEnabled(false);
    }
//
//
//    //function to set menu item visibility
//        public void EnableMenu(boolean enable){
//            Menu menu = navigationView.getMenu();
//            MenuItem item=menu.findItem(R.id.nav_hire);
//            item.setVisible(enable);
//            item=menu.findItem(R.id.nav_jobs);
//            item.setVisible(enable);
//            item=menu.findItem(R.id.nav_home);
//            item.setVisible(enable);
//        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
        private ActivityMapsBinding binding;
        private LatLng eventLocation;
        FloatingActionButton fabOk;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = ActivityMapsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            fabOk = findViewById(R.id.fabOk);
            fabOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent();
                    intent.putExtra("Location",eventLocation);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            });
        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng kandy = new LatLng(7.334, 80.6301);
            mMap.addMarker(new MarkerOptions().position(kandy).title("You are in in kandy"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(kandy));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kandy,18.0f));
            mMap.getUiSettings().setZoomControlsEnabled(true );
            eventLocation=null;
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    eventLocation = latLng;
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("evenet Location").
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18.0f));
                }
            });
        }
    }
}