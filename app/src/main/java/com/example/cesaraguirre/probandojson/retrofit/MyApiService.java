package com.example.cesaraguirre.probandojson.retrofit;

import com.example.cesaraguirre.probandojson.retrofit.io.ResponceError;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {
    @GET("extIDUsr.php")
    Call<ResponceError> getIdExist(@Query("IDusr") String IDusr);


    //regUsr2.php?contra=123&usr=cegdffdfderesar&nick=Cesa4r4
    @GET("regUsr2.php")
    Call<ResponceError> getRegUsr (@Query("contra") String contra ,
                                   @Query("usr") String usr,
                                   @Query("nick") String nick);

}
