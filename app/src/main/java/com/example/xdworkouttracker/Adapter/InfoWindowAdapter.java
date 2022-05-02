package com.example.xdworkouttracker.Adapter;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xdworkouttracker.databinding.InfoWindowLayoutBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.SphericalUtil;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private InfoWindowLayoutBinding binding;
    private Location location;
    private Context context;

    public InfoWindowAdapter(Location location, Context context) {
        this.location = location;
        this.context = context;

        binding = InfoWindowLayoutBinding.inflate(LayoutInflater.from(context), null, false);

    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        binding.txtLocationName.setText(marker.getTitle());

        double distance = SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(), location.getLongitude()),
                marker.getPosition());

        if(distance > 1000){
            double kilometers = distance / 1000;
            binding.txtLocationDistance.setText((int)Math.ceil(distance) + " KM");
        }else{
            binding.txtLocationDistance.setText((int)Math.ceil(distance) + " Meters");
        }
        location.setSpeed((float) 1.5);
        float speed = location.getSpeed();

        if(speed > 0 ){
            int time = (int) Math.ceil((distance / speed) / 60);
            binding.txtLocationTime.setText(time + " mins");
        }else {
            binding.txtLocationTime.setText("N/A");
        }
        return binding.getRoot();
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        binding.txtLocationName.setText(marker.getTitle());

        double distance = SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(), location.getLongitude()),
                marker.getPosition());

        if(distance > 1000){
            double kilometers = distance / 1000;
            binding.txtLocationDistance.setText((int)Math.ceil(distance) + " KM");
        }else{
            binding.txtLocationDistance.setText((int)Math.ceil(distance) + " Meters");
        }
        location.setSpeed((float) 1.5);
        float speed = location.getSpeed();

        if(speed > 0 ){
            int time = (int) Math.ceil((distance / speed) / 60);
            binding.txtLocationTime.setText(time + " mins");
        }else {
            binding.txtLocationTime.setText("N/A");
        }
        return binding.getRoot();
    }
}
