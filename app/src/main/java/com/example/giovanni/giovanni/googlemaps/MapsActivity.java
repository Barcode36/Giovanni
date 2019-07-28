package com.example.giovanni.giovanni.googlemaps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.giovanni.giovanni.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText editLocation;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // GEOCODING
        Button geolocationButton = findViewById(R.id.button_search);
        geolocationButton.setOnClickListener(v -> {
            editLocation = findViewById(R.id.edit_search);
            location = editLocation.getText().toString();
            if(!location.equals("")) {
                List<Address> addressList = null;
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    addressList = geocoder.getFromLocationName(location, 5);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
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

        // Add a marker in Giugliano in Campania and move the camera
        LatLng giugliano = new LatLng(40.9461440, 14.1311960);
        mMap.addMarker(new MarkerOptions().position(giugliano).title("Giugliano in Campania"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(giugliano));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(giugliano, 5));
        // NOTA: 5 indica lo zoom all'avvio sul marker (in questo caso Giugliano in Campania) e può essere cambiato.
    }
}
