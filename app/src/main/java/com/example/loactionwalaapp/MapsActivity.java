package com.example.loactionwalaapp;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.loactionwalaapp.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final String TAG = "MapsActivity";



    // widgets
    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"MapsActivity : created");

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSearchText =(EditText)findViewById(R.id.input_search);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init(){
        Log.d(TAG,"init : initializing ");
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId== EditorInfo.IME_ACTION_DONE
                ||event.getAction()==KeyEvent.ACTION_DOWN
                ||event.getAction()== KeyEvent.KEYCODE_ENTER) {
                    Log.d(TAG,"inside onEditorAction");
                    // execute our method for searching
                    geoLocate();

                }
                Log.d(TAG,"outside onEditorAction");
                return false;
            }
        });
    }
    private void geoLocate(){
        Log.d(TAG,"geoLocate : geolocating");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address>list = new ArrayList<>();
        try {
            list =geocoder.getFromLocationName(searchString,1);
        } catch (IOException e) {
            Log.e(TAG,"geoLocate IOException" +e.getMessage());
        }

        if(list.size()>0){
            Address address =list.get(0);
            Log.d(TAG, "location---->"+address.toString());
            LatLng searched_location = new LatLng(address.getLatitude(),address.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(searched_location));
            mMap.addMarker(new MarkerOptions().position(searched_location).title(address.getLocality()));

            Toast.makeText(this,address.toString(),Toast.LENGTH_SHORT);

        }
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
        //GPSTracker cur=null;
        //Location C =cur.getLocation();
        init();


        LatLng sydney = new LatLng(23.2599,77.4126);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Bhopal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}