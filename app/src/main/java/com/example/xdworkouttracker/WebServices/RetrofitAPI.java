package com.example.xdworkouttracker.WebServices;

import com.example.xdworkouttracker.Model.GoogleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<GoogleResponseModel> getNearByPlaces(@Url String url);
}
