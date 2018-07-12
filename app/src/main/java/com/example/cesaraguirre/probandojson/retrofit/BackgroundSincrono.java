package com.example.cesaraguirre.probandojson.retrofit;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.cesaraguirre.probandojson.UsrRegistActivity;
import com.example.cesaraguirre.probandojson.retrofit.io.ResponceError;

import java.io.IOException;

import retrofit2.Call;

public class BackgroundSincrono extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BackgroundSincrono(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Call<ResponceError> call = MyApiAdapter.getApiService().getIdExist(intent.getStringExtra("nombre"));
            ResponceError error = call.execute().body();

            if (error.isError()){


            }else{

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
