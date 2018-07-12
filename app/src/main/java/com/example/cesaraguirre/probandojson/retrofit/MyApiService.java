package com.example.cesaraguirre.probandojson.retrofit;

import com.example.cesaraguirre.probandojson.retrofit.io.ResponceError;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {
    @GET("extIDUsr.php")
    Call<ResponceError> getIdExist(@Query("IDusr") String IDusr);

}
