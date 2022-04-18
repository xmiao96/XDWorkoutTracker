package com.example.xdworkouttracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.GooglePlaceModel;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.databinding.PlaceItemLayoutBinding;

import java.util.List;

public class GooglePlaceAdapter extends RecyclerView.Adapter<GooglePlaceAdapter.ViewHolder> {

    private List<GooglePlaceModel> googlePlaceModelList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaceItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.place_item_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(googlePlaceModelList != null){
                GooglePlaceModel placeModel = googlePlaceModelList.get(position);
                holder.binding.setGooglePlaceModel(placeModel);
            }
    }

    @Override
    public int getItemCount() {
        if(googlePlaceModelList != null)
            return googlePlaceModelList.size();
        else
        return 0;
    }

    public void setGooglePlaceModelList(List<GooglePlaceModel> googlePlaceModelList) {
        this.googlePlaceModelList = googlePlaceModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private PlaceItemLayoutBinding binding;
        public ViewHolder(@NonNull PlaceItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
