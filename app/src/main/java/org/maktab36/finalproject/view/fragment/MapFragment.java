package org.maktab36.finalproject.view.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentMapBinding;
import org.maktab36.finalproject.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class MapFragment extends Fragment {

    private FragmentMapBinding mMapBinding;
    private GoogleMap mGoogleMap;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        mMapBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_map, container, false);

        return mMapBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapBinding.mapViewAddress.onCreate(savedInstanceState);
        mMapBinding.mapViewAddress.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                setListeners();
            }
        });
    }

    private void setListeners() {
        mGoogleMap.setOnMapLongClickListener(latLng -> {
            mGoogleMap.clear();
            MarkerOptions marker = new MarkerOptions().position(latLng);
            mGoogleMap.addMarker(marker);
            Log.d("reza4", latLng.toString());

            /*Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            List<Address> address=new ArrayList<>();
            try {
                address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), address.toString(), Toast.LENGTH_SHORT).show();*/
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mMapBinding.mapViewAddress.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapBinding.mapViewAddress.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapBinding.mapViewAddress.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapBinding.mapViewAddress.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapBinding.mapViewAddress.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapBinding.mapViewAddress.onSaveInstanceState(outState);
    }


}