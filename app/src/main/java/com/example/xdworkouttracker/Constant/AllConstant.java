package com.example.xdworkouttracker.Constant;

import com.example.xdworkouttracker.PlaceModel;
import com.example.xdworkouttracker.R;

import java.util.ArrayList;
import java.util.Arrays;

public interface AllConstant {

    int STORAGE_REQUEST_CODE = 1000;
    int LOCATION_REQUEST_CODE = 2000;

    ArrayList<PlaceModel> placesName = new ArrayList<>(
            Arrays.asList(
                    new PlaceModel(1, R.drawable.ic_baseline_fitness_center_24, "Gym", "gym")
//                    new PlaceModel(2, R.drawable.ic_restaurant, "Restaurant", "restaurant")
//                    new PlaceModel(3, R.drawable.ic_atm, "ATM", "atm"),
//                    new PlaceModel(4, R.drawable.ic_gas_station, "Gas", "gas_station"),
//                    new PlaceModel(5, R.drawable.ic_shopping_cart, "Groceries", "supermarket"),
//                    new PlaceModel(6, R.drawable.ic_hotel, "Hotels", "hotel"),
//                    new PlaceModel(7, R.drawable.ic_pharmacy, "Pharmacies", "pharmacy"),
//                    new PlaceModel(8, R.drawable.ic_hospital, "Hospitals & Clinics", "hospital"),
//                    new PlaceModel(9, R.drawable.ic_car_wash, "Car Wash", "car_wash"),
//                    new PlaceModel(10, R.drawable.ic_saloon, "Beauty Salons", "beauty_salon")

            )
    );
}
